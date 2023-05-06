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
import ulaval.glo2003.application.repository.SellerMongoRepository;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.entities.Seller;

@Path("/sellers")
public class SellerRessource {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    public SellerMongoRepository sellerMongoRepository;

    public SellerRessource(SellerRepository sellerRepository, SellerMapper sellerMapper, SellerMongoRepository sellerMongoRepository) {
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
        this.sellerMongoRepository = sellerMongoRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seller(SellerRequest sellerRequest) {
        Seller sellerCreated = sellerMapper.mapRequestToEntity(sellerRequest);

        // how to update a seller
        sellerRepository.save(sellerCreated);
        sellerMongoRepository.save(sellerCreated);

        URI location = UriBuilder.fromPath("/sellers/{id}").build(sellerCreated.getId());
        return Response.created(location).status(201).build();
    }

    // this is not dead code, this is very usefull for testing with postman
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSellers() {
        List<SellerResponse> sellerResponses =
                this.sellerRepository.findAll().stream()
                        .map(sellerMapper::mapEntityToResponse)
                        .collect(Collectors.toList());
        return Response.ok(sellerResponses).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sellerId}")
    public Response getSeller(@PathParam("sellerId") String sellerId) {

        Seller foundSeller = this.sellerMongoRepository.getSellerById(sellerId);

        SellerResponse sellerResponse = sellerMapper.mapEntityToResponse(foundSeller);

        return Response.ok(sellerResponse).build();
    }
}
