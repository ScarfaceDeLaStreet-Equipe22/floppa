package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

import ulaval.glo2003.api.Mappers.SellerMapper;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundSellerIdException;
import ulaval.glo2003.api.Seller.SellerRequest;
import ulaval.glo2003.api.Seller.SellerResponse;
import ulaval.glo2003.api.Validators.SellerRequestValidator;
import ulaval.glo2003.application.SellerRepository;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerClasses.SellerParamsValidator;

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

        return Response.status(201).entity(sellerCreated).build();
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

        Seller foundSeller = this.sellerRepository.findById(sellerId);

        SellerMapper sellerMapper = new SellerMapper();
        SellerResponse sellerResponse = sellerMapper.mapEntityToResponse(foundSeller);

        return Response.ok(sellerResponse).build();
    }
}
