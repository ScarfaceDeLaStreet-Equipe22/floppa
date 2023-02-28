package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;

import ulaval.glo2003.api.Mappers.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.api.Offer.OfferRequest;
import ulaval.glo2003.api.Product.ProductListResponse;
import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Product.ProductResponse;
import ulaval.glo2003.api.Validators.ProductRequestValidator;
import ulaval.glo2003.application.ProductRepository;
import ulaval.glo2003.application.SellerRepository;
import ulaval.glo2003.domain.*;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductClasses.ProductFilter;

@Path("/products")
public class ProductRessource {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    public ProductRessource(SellerRepository sellerRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @POST
    @Path("{Productid}/offers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response offre(
            OfferRequest request,
            @PathParam("Productid") String productId,
            @HeaderParam("X-Buyer-Username") String buyerUsername) {

        Product productForOffer = productRepository.findById(productId);

        OfferValidator offerValidator = new OfferValidator(request.getAmount(), request.getMessage(), buyerUsername, productForOffer);

        Offer offer =
                new Offer(
                        offerValidator.getAmount(),
                        offerValidator.getMessage(),
                        offerValidator.getBuyerUsername());

        productForOffer.addOffer(offer);

        return Response.status(201).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductRequest productRequest, @HeaderParam("X-Seller-Id") String sellerId) {

        ProductRequestValidator productRequestValidator = new ProductRequestValidator(productRequest);
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
                                                product.getSuggestedPrice(),
                                                product.getId(),
                                                product.getCreatedAt(),
                                                product.getSeller(),
                                                product.getNumberOfOffers(),
                                                product.getAverageAmountOfOffers()))
                        .collect(Collectors.toList());

        return Response.ok(new ProductListResponse(filteredProducts)).build();
    }
}

