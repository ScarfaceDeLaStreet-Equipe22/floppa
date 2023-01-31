package ulaval.glo2003.Seller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sellers")
public class SellerRessource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest){
        SellerResponse sellerResponse = new SellerResponse(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);
        Seller seller = new Seller(sellerRequest.name, sellerRequest.bio, sellerRequest.birthDate);
        return Response.ok(seller).build();
    }
}
