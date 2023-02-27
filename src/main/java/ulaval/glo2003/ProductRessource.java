package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import ulaval.glo2003.api.Offer.OfferRequest;
import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Product.ProductResponse;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundProductIdException;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundSellerIdException;
import ulaval.glo2003.api.ProductExceptions.MissingSellerIdException;
import ulaval.glo2003.domain.*;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;
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
    public Response createProduct(
            ProductRequest request, @HeaderParam("X-Seller-Id") String sellerId) {

        Product product;
        if (sellerId.isEmpty()) {
            throw new MissingSellerIdException();
        }
        Seller seller = getSeller(sellerId);
        ProductCategory productCategory = new ProductCategory(request.getCategory());
        Amount suggestedPrice = new Amount(request.getSuggestedPrice());

        ProductParameterValidator productParameterValidator =
                new ProductParameterValidator(
                        request.getTitle(),
                        request.getDescription(),
                        productCategory,
                        suggestedPrice);
        product =
                new Product(
                        productParameterValidator.getTitle(),
                        productParameterValidator.getDescription(),
                        productParameterValidator.getCategory(),
                        productParameterValidator.getSuggestedPrice(),
                        seller);

        seller.addProduct(product);
        products.add(product);

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
            throw new ItemNotFoundSellerIdException();
        }
        return sellerNeeded;
    }

    public Product getProduct(String id) {
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
