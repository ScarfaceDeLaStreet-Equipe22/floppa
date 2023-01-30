package ulaval.glo2003;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Path;
@Path("/products")
public class CreationProduit {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pong(ProductRequest request){

//        if (!request.isEmpty()){
        ProductResponse response = new ProductResponse(request.title) ;
        Produit produit = new Produit(request.title,request.description,request.suggestedPrice,request.category) ;
        return Response.ok(produit).build();
//        throw new RuntimeException("wow non") ;
//        }
//        return Response.ok("rien").build();
    }

    
}
