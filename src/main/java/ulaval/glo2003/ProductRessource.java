package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import ulaval.glo2003.api.exceptions.MissingParamException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingParamBuyerUsername;
import ulaval.glo2003.api.mappers.OfferMapper;
import ulaval.glo2003.api.mappers.ProductFiltersMapper;
import ulaval.glo2003.api.mappers.ProductMapper;
import ulaval.glo2003.api.requests.OfferRequest;
import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.api.requests.SellProductRequest;
import ulaval.glo2003.api.responses.ProductListResponse;
import ulaval.glo2003.api.responses.ProductResponse;
import ulaval.glo2003.application.repository.ProductRepository;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.ItemIsSoldException;
import ulaval.glo2003.domain.utils.ProductFilters;

@Path("/products")
public class ProductRessource {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductFiltersMapper productFiltersMapper;
    private final OfferMapper offerMapper;

    public ProductRessource(
            SellerRepository sellerRepository,
            ProductRepository productRepository,
            ProductMapper productMapper,
            OfferMapper offerMapper,
            ProductFiltersMapper productFiltersMapper) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.offerMapper = offerMapper;
        this.productFiltersMapper = productFiltersMapper;
    }

    @POST
    @Path("{Productid}/offers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOffre(
            OfferRequest offerRequest,
            @PathParam("Productid") String productId,
            @HeaderParam("X-Buyer-Username") String buyerUsername) {

        Product productForOffer = productRepository.findById(productId);

        Offer offer = offerMapper.mapRequestToEntity(offerRequest, buyerUsername, productForOffer);

        productForOffer.offers.add(offer);

        return Response.status(201).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(
            ProductRequest productRequest, @HeaderParam("X-Seller-Id") String sellerId) {
        Seller seller = sellerRepository.findById(sellerId);

        Product productCreated = productMapper.mapRequestToEntity(productRequest, seller);

        seller.addProduct(productCreated);
        productRepository.save(productCreated);

        String url = "http://localhost:8080/products/" + productCreated.getId();

        return Response.created(URI.create(url)).build();
    }

    @GET
    @Path("{Productid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProducts(@PathParam("Productid") String productId) {
        Product product = productRepository.findById(productId);

        ProductResponse productResponse = productMapper.mapEntityToResponse(product);

        return Response.ok(productResponse).build();
    }

    @GET
    public Response getFilteredProducts(
            @QueryParam("sellerId") String sellerId,
            @QueryParam("title") String title,
            @QueryParam("category") String categoryName,
            @QueryParam("minPrice") String minPrice,
            @QueryParam("maxPrice") String maxPrice) {
        ProductFilters productFilters =
                productFiltersMapper.mapQueryParamsToRequest(
                        sellerId, title, categoryName, minPrice, maxPrice);

        List<ProductResponse> filteredProducts =
                productRepository.findAll().stream()
                        .filter(productFilters::checkProduct)
                        .map(productMapper::mapEntityToResponse)
                        .collect(Collectors.toList());

        return Response.ok(new ProductListResponse(filteredProducts)).build();
    }

    @POST
    @Path("{Productid}/sell")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sellProduct(
            SellProductRequest sellProductRequest,
            @PathParam("Productid") String productId,
            @HeaderParam("X-Seller-Id") String sellerId) {

        if (sellProductRequest.username == null) {
            throw new MissingParamBuyerUsername();
        }

        Product productToSell = productRepository.findById(productId);

        if (productToSell.getSaleStatus() == "sold") {
            throw new ItemIsSoldException();
        }

        List<Offer> filteredOffers = productToSell.offers.stream()
                .filter(offer -> offer.buyerUsername.equals(sellProductRequest.username))
                .collect(Collectors.toList());

        if (filteredOffers.size() == 0) {
            throw new ItemNotFoundException("username has no offer of product");
        }

        productToSell.getSold(filteredOffers.get(0).getBuyerUsername(), filteredOffers.get(0).getAmount());

        return Response.status(200).build();
    }
}
