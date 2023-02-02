package ulaval.glo2003.Seller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.Utils.MissingParamException;

@Path("/sellers")
public class SellerRessource {

    public SellerRessource(){
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest){

        Seller seller;
        String name = sellerRequest.name;
        String bio = sellerRequest.bio;
        String birthDate = sellerRequest.birthDate;

        SellerParams sellerParams = new SellerParams(name, bio, birthDate);
        seller = new Seller(sellerParams.name, sellerParams.bio, sellerParams.birthDate);


        return Response.status(201).entity(seller).build();
    }


}
