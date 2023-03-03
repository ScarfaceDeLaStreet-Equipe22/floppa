package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.api.mappers.OfferMapper;
import ulaval.glo2003.api.mappers.ProductMapper;
import ulaval.glo2003.api.requests.OfferRequest;
import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.api.responses.ProductListResponse;
import ulaval.glo2003.api.responses.ProductResponse;
import ulaval.glo2003.api.validators.OfferRequestValidator;
import ulaval.glo2003.api.validators.ProductRequestValidator;
import ulaval.glo2003.application.repository.ProductRepository;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.utils.ProductFilter;

@Path("/products")
public class ProductRessource {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    public ProductRessource(
            SellerRepository sellerRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @POST
    @Path("{Productid}/offers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOffre(
            OfferRequest offerRequest,
            @PathParam("Productid") String productId,
            @HeaderParam("X-Buyer-Username") String buyerUsername) {

        Product productForOffer = productRepository.findById(productId);



        OfferMapper offerMapper = new OfferMapper();
        Offer offer = offerMapper.mapRequestToEntity(offerRequest, buyerUsername, productForOffer);

        productForOffer.offers.add(offer);

        return Response.status(201).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(
            ProductRequest productRequest, @HeaderParam("X-Seller-Id") String sellerId) {

        ProductRequestValidator productRequestValidator =
                new ProductRequestValidator(productRequest);
        productRequestValidator.validateRequest();

        Seller seller = sellerRepository.findById(sellerId);
        ProductMapper productMapper = new ProductMapper();
        Product productCreated = productMapper.mapRequestToEntity(productRequest, seller);

        seller.addProduct(productCreated);
        productRepository.save(productCreated);

        String url = "http://localhost:8080/Products/" + sellerId;

        return Response.created(URI.create(url)).build();
    }

    @GET
    @Path("{Productid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProducts(@PathParam("Productid") String productId) {

        Product product = productRepository.findById(productId);
        ProductMapper productMapper = new ProductMapper();
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
        ProductFilter productFilter =
                new ProductFilter(sellerId, title, categoryName, minPrice, maxPrice);

        List<ProductResponse> filteredProducts =
                productRepository.findAll().stream()
                        .filter(productFilter::checkProduct)
                        .map(
                                product ->
                                        new ProductResponse(
                                                product.getTitle(),
                                                product.getDescription(),
                                                product.getCategory(),
                                                product.getSuggestedPrice().toDouble(),
                                                product.getId(),
                                                product.getCreatedAt(),
                                                product.getSeller(),
                                                product.getNumberOfOffers(),
                                                product.getAverageAmountOfOffers()))
                        .collect(Collectors.toList());

        return Response.ok(new ProductListResponse(filteredProducts)).build();
    }
}
