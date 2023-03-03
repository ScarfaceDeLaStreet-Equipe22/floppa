package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import ulaval.glo2003.api.mappers.SellerMapper;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.SellerResponse;
import ulaval.glo2003.api.validators.SellerRequestValidator;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.entities.Seller;

@Path("/sellers")
public class SellerRessource {

    private final SellerRepository sellerRepository;

    public SellerRessource(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest) {

        SellerRequestValidator sellerRequestValidator = new SellerRequestValidator(sellerRequest);
        sellerRequestValidator.validateRequest();

        SellerMapper sellerMapper = new SellerMapper();
        Seller sellerCreated = sellerMapper.mapRequestToEntity(sellerRequest);

        sellerRepository.save(sellerCreated);

        URI location = UriBuilder.fromPath("/sellers/{id}").build(sellerCreated.getId());
        return Response.created(location).status(201).build();
    }

    @GET
    public Response getAllSellers() {
        List<SellerResponse> sellerResponses =
                this.sellerRepository.findAll().stream()
                        .map(
                                seller ->
                                        new SellerResponse(
                                                seller.getId(),
                                                seller.getName(),
                                                seller.getBio(),
                                                seller.getBirthdate().getDate(),
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

        Seller foundSeller = this.sellerRepository.findById(sellerId);

        SellerMapper sellerMapper = new SellerMapper();
        SellerResponse sellerResponse = sellerMapper.mapEntityToResponse(foundSeller);

        return Response.ok(sellerResponse).build();
    }
}
