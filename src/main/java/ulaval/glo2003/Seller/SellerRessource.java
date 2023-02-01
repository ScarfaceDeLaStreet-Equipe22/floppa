package ulaval.glo2003.Seller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.Utils.MissingParamException;

@Path("/sellers")
public class SellerRessource {
    SellerFactory sellerFactory;

    public SellerRessource(SellerFactory sellerFactory){
        this.sellerFactory = sellerFactory;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest){
        String name = sellerRequest.name;
        String bio = sellerRequest.bio;
        String birthDate = sellerRequest.birthDate;

        //validateSellerRequest(sellerRequest);
        SellerParams sellerParams = new SellerParams(name, bio, birthDate);
        Seller seller = sellerFactory.makeSeller(sellerParams);

        return Response.ok(seller).build();
    }


}
