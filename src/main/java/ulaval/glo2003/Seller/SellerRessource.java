package ulaval.glo2003.Seller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.Utils.MissingParamException;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/sellers")
public class SellerRessource {

    private final ArrayList<Seller> sellers;

    public SellerRessource(){
        this.sellers = new ArrayList<>();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest){

        Seller seller;
        String name = sellerRequest.name;
        String bio = sellerRequest.bio;
        String birthDate = sellerRequest.birthdate;

        SellerParams sellerParams = new SellerParams(name, bio, birthDate);
        seller = new Seller(sellerParams.name, sellerParams.bio, sellerParams.birthdate);

        sellers.add(seller);

        return Response.status(201).entity(seller).build();
    }

    @GET
    public Response getAllSellers(){
        List<SellerResponse > sellerResponses = this.sellers
                .stream()
                .map(seller -> new SellerResponse(seller.getId(), seller.getName(), seller.getBio()))
                .collect(Collectors.toList());
        return Response.ok(sellerResponses).build();
    }

    @GET
    @Path("{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId){
        Seller foundSeller = this.sellers
                .stream()
                .filter(seller -> seller.getId().equals(sellerId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Seller not found."));

        SellerResponse response = new SellerResponse(foundSeller.getId(), foundSeller.getName(), foundSeller.getBio());
        return Response.ok(response).build();
    }



}
