package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import ulaval.glo2003.api.mappers.BuyerMapper;
import ulaval.glo2003.api.requests.BuyerRequest;
import ulaval.glo2003.api.responses.BuyerResponse;
import ulaval.glo2003.application.repository.BuyerMongoRepository;
import ulaval.glo2003.application.repository.BuyerRepository;
import ulaval.glo2003.application.repository.SellerMongoRepository;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.entities.Buyer;

import java.net.URI;

@Path("/buyers")
public class BuyerRessource {

    private final BuyerMapper buyerMapper;
    public BuyerRepository buyerRepository;
    public BuyerMongoRepository buyerMongoRepository;

    public BuyerRessource(BuyerMongoRepository buyerMongoRepository, BuyerMapper buyerMapper) {
        this.buyerMongoRepository = buyerMongoRepository;
        this.buyerMapper = buyerMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buyer(BuyerRequest buyerRequest) {
        Buyer buyerCreated = buyerMapper.mapRequestToEntity(buyerRequest);

        buyerMongoRepository.save(buyerCreated);

        URI location = UriBuilder.fromPath("/buyers/{id}").build(buyerCreated.getId());
        return Response.created(location).status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{buyerId}")
    public Response getBuyer(@PathParam("buyerId") String buyerId) {
        Buyer foundBuyer = this.buyerMongoRepository.getBuyerById(buyerId);

        BuyerResponse buyerResponse = buyerMapper.mapEntityToResponse(foundBuyer);

        return Response.ok(buyerResponse).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/recommendation/{buyerId}")
    public Response getRecommendation(@PathParam("buyerId") String buyerId){
        List<BuyerResponse> buyerResponses =
                this.buyerRepository.findAll().stream()
                        .map(buyerMapper::mapEntityToResponse)
                        .collect(Collectors.toList());
        return Response.ok(buyerResponses).build();
    }
}
