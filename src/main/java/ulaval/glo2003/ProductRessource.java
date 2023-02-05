package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.Domain.*;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;
import ulaval.glo2003.api.Product.ProductRequest;

import java.net.URI;
import java.util.ArrayList;

@Path("/products")
public class ProductRessource {


    private final ArrayList<Seller> sellers;
    public ProductRessource(ArrayList<Seller> sellers) {
        this.sellers = sellers ;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response pong(ProductRequest request, @HeaderParam("X-Seller-Id") String sellerId, @PathParam("id") String id) {

        Product product ;

        ProductCategory productCategory = new ProductCategory(request.getCategory());
        Amount suggestedPrice = new Amount(request.getSuggestedPrice()) ;

        ProductParameterValidator V = new ProductParameterValidator(request.getTitle(), request.getDescription(), productCategory, suggestedPrice);

        product = new Product(V.getTitle(), V.getDescription(), V.getCategory(), V.getSuggestedPrice());


        Seller seller = getSeller(sellerId) ;

        seller.addProduct(product);

        String url = "http://localhost:8080/Products/"+ sellerId ;

        return Response.created(URI.create(url)).build() ;
    }


    public Seller getSeller(String id){
        Seller sellerNeeded = null ;
        for(Seller seller : sellers){
            if(seller.getId().equals(id)){
                sellerNeeded = seller ;
            }
        }
        if (sellerNeeded == null){
            throw new ItemNotFoundException() ;
        }
        return sellerNeeded ;
    }



}
