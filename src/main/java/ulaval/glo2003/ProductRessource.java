package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;

import ulaval.glo2003.api.Mappers.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.api.Offer.OfferRequest;
import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Product.ProductResponse;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;
import ulaval.glo2003.domain.*;
import ulaval.glo2003.domain.Product;

import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;
import ulaval.glo2003.domain.ProductClasses.ProductParameterValidator;

@Path("/products")
public class ProductRessource {

    private final ArrayList<Seller> sellers; //TODO ICI DEVRAIT ETRE LE SELLERREPOSITORY INJECTÉ
    private final ArrayList<Product> products; //TODO ICI DEVRAIT ETRE LE PRODUCTREPOSITORY INJECTÉ

    public ProductRessource(ArrayList<Seller> sellers, ArrayList<Product> products) {
        this.sellers = sellers;
        this.products = products;
    }

    @POST
    @Path("{Productid}/offers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response offre(
            OfferRequest request,
            @PathParam("Productid") String productId,
            @HeaderParam("X-Buyer-Username") String buyerUsername) {

        Product productForOffer = getProduct(productId);

        OfferValidator offerValidator =
                new OfferValidator(
                        request.getAmount(), request.getMessage(), buyerUsername, productForOffer);

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

        Seller seller = getSeller(sellerId);
        ProductMapper productMapper = new ProductMapper();
        Product productCreated = productMapper.mapRequestToEntity(productRequest, seller); //TODO ICI DEVRAIT USE LE SELLERREPOSITORY

        seller.addProduct(productCreated); //TODO ajout des Repository
        products.add(productCreated);

        String url = "http://localhost:8080/Products/" + sellerId;

        return Response.created(URI.create(url)).build();
    }

    @GET
    @Path("{Productid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProducts(@PathParam("Productid") String productId) {

        Product product = getProduct(productId);
        ProductResponse productResponse = (new ProductMapper()).mapEntityToResponse(product);

        ProductResponse productResponse =
                new ProductResponse(
                        productNeeded.getTitle(),
                        productNeeded.getDescription(),
                        productNeeded.getCategory(),
                        productNeeded.getSuggestedPrice(),
                        productNeeded.getId(),
                        productNeeded.getCreatedAt(),
                        productNeeded.getSeller(),
                        productNeeded.getNumberOfOffers(),
                        productNeeded.getAverageAmountOfOffers());
        return Response.ok(productResponse).build();
    }

    public Seller getSeller(String id) { //TODO PRENDRE LA METHODE FINDBYID DANS LE SELLERREPOSITORY DONC À SUPP EVENTUELLEMENT
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
                products.stream()
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
        ;

        return Response.ok(new ProductListResponse(filteredProducts)).build();
    }

    public Seller getSeller(String id) {
        Seller sellerNeeded = null;
        for (Seller seller : sellers) {
            if (seller.getId().equals(id)) {
                sellerNeeded = seller;
            }
        }
        if (sellerNeeded == null) {
            throw new ItemNotFoundSellerIdException();
        }
        return sellerNeeded;
    }

    public Product getProduct(String id) { //TODO PRENDRE LA METHODE FINDBYID DANS LE PRODUCTREPOSITORY DONC À SUPP EVENTUELLEMENT
        Product productNeeded = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                productNeeded = product;
            } else {
                throw new ItemNotFoundProductIdException();
            }
        }
        return productNeeded;
    }

}
