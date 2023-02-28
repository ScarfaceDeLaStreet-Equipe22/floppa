package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundSellerIdException;
import ulaval.glo2003.api.Seller.SellerRequest;
import ulaval.glo2003.api.Seller.SellerResponse;
import ulaval.glo2003.api.Validators.SellerRequestValidator;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerClasses.SellerParamsValidator;

@Path("/sellers")
public class SellerRessource {

    private final ArrayList<Seller> sellers;

    public SellerRessource(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest) {

        Seller seller;
        String name = sellerRequest.name;
        String bio = sellerRequest.bio;
        String birthDate = sellerRequest.birthdate;
        String email = sellerRequest.email;
        String phoneNumber = sellerRequest.phoneNumber;

        SellerRequestValidator sellerRequestValidator = new SellerRequestValidator(sellerRequest);
        sellerRequestValidator.validateRequest();

        SellerParamsValidator sellerParams =
                new SellerParamsValidator(name, bio, birthDate, email, phoneNumber);
        seller =
                new Seller(
                        sellerParams.name,
                        sellerRequest.birthdate,
                        sellerRequest.email,
                        sellerRequest.phoneNumber,
                        sellerParams.bio);

        sellers.add(seller);

        return Response.status(201).entity(seller).build();
    }

    @GET
    public Response getAllSellers() {
        List<SellerResponse> sellerResponses =
                this.sellers.stream()
                        .map(
                                seller ->
                                        new SellerResponse(
                                                seller.getId(),
                                                seller.getName(),
                                                seller.getBio(),
                                                seller.getBirthDate(),
                                                seller.getEmail(),
                                                seller.getPhoneNumber(),
                                                seller.getProducts(),
                                                seller.getCreatedAt()))
                        .collect(Collectors.toList());
        return Response.ok(sellerResponses).build();
    }

    @GET
    @Path("{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId) {
        Seller foundSeller =
                this.sellers.stream()
                        .filter(seller -> seller.getId().equals(sellerId))
                        .findFirst()
                        .orElseThrow(() -> new ItemNotFoundSellerIdException());

        SellerResponse response =
                new SellerResponse(
                        foundSeller.getId(),
                        foundSeller.getName(),
                        foundSeller.getBio(),
                        foundSeller.getBirthDate(),
                        foundSeller.getEmail(),
                        foundSeller.getPhoneNumber(),
                        foundSeller.getProducts(),
                        foundSeller.getCreatedAt());
        return Response.ok(response).build();
    }
}
