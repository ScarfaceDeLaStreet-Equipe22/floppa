package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.api.Offer.OfferRequest;
import ulaval.glo2003.api.Product.ProductListResponse;
import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Product.ProductResponse;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;
import ulaval.glo2003.domain.*;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;
import ulaval.glo2003.domain.ProductClasses.ProductFilter;
import ulaval.glo2003.domain.ProductClasses.ProductParameterValidator;

@Path("/products")
public class ProductRessource {

    private final ArrayList<Seller> sellers;
    private final ArrayList<Product> products;

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

        Offer offer = new Offer(request.getAmount(), request.getMessage(), buyerUsername);

        Product productNeeded = getProduct(productId);

        productNeeded.addOffer(offer);

        return Response.status(201).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(
            ProductRequest request, @HeaderParam("X-Seller-Id") String sellerId) {

        Product newProduct;

        ProductCategory productCategory = new ProductCategory(request.getCategory());
        Amount suggestedPrice = new Amount(request.getSuggestedPrice());

        ProductParameterValidator productParameterValidator =
                new ProductParameterValidator(
                        request.getTitle(),
                        request.getDescription(),
                        productCategory,
                        suggestedPrice);

        Seller seller = getSeller(sellerId);
        newProduct =
                new Product(
                        productParameterValidator.getTitle(),
                        productParameterValidator.getDescription(),
                        productParameterValidator.getCategory(),
                        productParameterValidator.getSuggestedPrice(),
                        seller);

        seller.addProduct(newProduct);
        products.add(newProduct);

        String url = "http://localhost:8080/Products/" + sellerId;

        return Response.created(URI.create(url)).build();
    }

    @GET
    @Path("{Productid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProducts(@PathParam("Productid") String productId) {

        Product productNeeded = getProduct(productId);

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
                allProducts.stream()
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
            throw new ItemNotFoundException();
        }
        return sellerNeeded;
    }

    public Product getProduct(String id) {
        Product productNeeded = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                productNeeded = product;
            } else {
                throw new ItemNotFoundException();
            }
        }
        return productNeeded;
    }
}
