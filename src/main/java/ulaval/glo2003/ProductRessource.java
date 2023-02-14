package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
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

    private final ArrayList<Seller> sellers;
    private ArrayList<Product> allProducts;

    public ProductRessource(ArrayList<Seller> sellers, ArrayList<Product> allProducts) {
        this.sellers = sellers;
        this.allProducts = allProducts;
    }

    @POST
    @Path("{Productid}/offers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response offre(
            OfferRequest request,
            @PathParam("Productid") String productId,
            @HeaderParam("X-Buyer-Username") String buyerUsername) {

        Offer offer = new Offer(request.amount, request.message, buyerUsername);

        Product productNeeded = getProduct(productId);

        productNeeded.addOffer(offer);

        return Response.status(201).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pong(ProductRequest request, @HeaderParam("X-Seller-Id") String sellerId) {

        Product product;

        ProductCategory productCategory = new ProductCategory(request.getCategory());
        Amount suggestedPrice = new Amount(request.getSuggestedPrice());

        ProductParameterValidator V =
                new ProductParameterValidator(
                        request.getTitle(),
                        request.getDescription(),
                        productCategory,
                        suggestedPrice);

        Seller seller = getSeller(sellerId);
        product =
                new Product(
                        V.getTitle(),
                        V.getDescription(),
                        V.getCategory(),
                        V.getSuggestedPrice(),
                        seller);

        seller.addProduct(product);
        allProducts.add(product);

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
        for (Product product : allProducts) {
            if (product.getId().equals(id)) {
                productNeeded = product;
            } else {
                throw new ItemNotFoundException();
            }
        }
        return productNeeded;
    }
}
