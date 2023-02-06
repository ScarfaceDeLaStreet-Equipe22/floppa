package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.Domain.Seller;
import ulaval.glo2003.Domain.SellerParamsValidator;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;
import ulaval.glo2003.api.Seller.SellerRequest;
import ulaval.glo2003.api.Seller.SellerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/sellers")
public class SellerRessource {

    private final ArrayList<Seller> sellers;

    public SellerRessource( ArrayList<Seller> sellers){
        this.sellers = sellers ;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest){

        Seller seller;
        String name = sellerRequest.name;
        String bio = sellerRequest.bio;
        String birthDate = sellerRequest.birthdate;

        SellerParamsValidator sellerParams = new SellerParamsValidator(name, bio, birthDate);
        seller = new Seller(
                sellerParams.name,
                sellerRequest.birthdate,
                sellerRequest.email,
                sellerRequest.phoneNumber,
                sellerParams.bio);

        sellers.add(seller);

        return Response.status(201).entity(seller).build();
    }

    @GET
    public Response getAllSellers(){
        List<SellerResponse> sellerResponses = this.sellers
                .stream()
                .map(seller -> new SellerResponse(
                        seller.getId(),
                        seller.getName(),
                        seller.getBio(),
                        seller.getBirthDate(),
                        seller.getEmail(),
                        seller.getPhoneNumber(),
                        seller.getProducts()))
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
                .orElseThrow(() -> new ItemNotFoundException());

        SellerResponse response = new SellerResponse(
                foundSeller.getId(),
                foundSeller.getName(),
                foundSeller.getBio(),
                foundSeller.getBirthDate(),
                foundSeller.getEmail(),
                foundSeller.getPhoneNumber(),
                foundSeller.getProducts());
        return Response.ok(response).build();
    }



}
