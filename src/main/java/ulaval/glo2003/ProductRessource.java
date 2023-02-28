package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;

import ulaval.glo2003.api.Mappers.ProductMapper;
import ulaval.glo2003.api.Offer.OfferRequest;
import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Product.ProductResponse;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;
import ulaval.glo2003.api.Validators.ProductRequestValidator;
import ulaval.glo2003.domain.*;
import ulaval.glo2003.domain.Product;


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

        Offer offer = new Offer(request.getAmount(), request.getMessage(), buyerUsername);

        Product productNeeded = getProduct(productId);

        productNeeded.addOffer(offer);

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

        return Response.ok(productResponse).build();
    }

    public Seller getSeller(String id) { //TODO PRENDRE LA METHODE FINDBYID DANS LE SELLERREPOSITORY DONC À SUPP EVENTUELLEMENT
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

    public Product getProduct(String id) { //TODO PRENDRE LA METHODE FINDBYID DANS LE PRODUCTREPOSITORY DONC À SUPP EVENTUELLEMENT
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
