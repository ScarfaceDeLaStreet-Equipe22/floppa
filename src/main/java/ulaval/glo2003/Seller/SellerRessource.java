package ulaval.glo2003.Seller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.Utils.MissingParamException;

@Path("/sellers")
public class SellerRessource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest){
        String name = sellerRequest.name;
        String bio = sellerRequest.bio;
        String birthDate = sellerRequest.birthDate;

        //validateSellerRequest(sellerRequest);
        Seller seller = new Seller(name, bio, birthDate);

        return Response.ok(seller).build();
    }

    /*private void validateSellerRequest(SellerRequest sellerRequest){
        if(sellerRequest.name == null || sellerRequest.bio == null || sellerRequest.birthDate == null){
            throw new MissingParamException("The name, bio and birthdate should not be blank");
        }
    }*/
}
