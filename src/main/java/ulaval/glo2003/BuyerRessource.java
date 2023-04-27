package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import ulaval.glo2003.api.mappers.BuyerMapper;
import ulaval.glo2003.api.mappers.SellerMapper;
import ulaval.glo2003.api.requests.BuyerRequest;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.BuyerResponse;
import ulaval.glo2003.api.responses.SellerResponse;
import ulaval.glo2003.application.repository.BuyerMongoRepository;
import ulaval.glo2003.application.repository.SellerMongoRepository;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.entities.Buyer;
import ulaval.glo2003.domain.entities.Seller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/buyers")
public class BuyerRessource {

    private final BuyerMapper buyerMapper;
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
    public Response getSeller(@PathParam("buyerId") String buyerId) {
        Buyer foundBuyer = this.buyerMongoRepository.getBuyerById(buyerId);

        BuyerResponse buyerResponse = buyerMapper.mapEntityToResponse(foundBuyer);

        return Response.ok(buyerResponse).build();
    }
}
