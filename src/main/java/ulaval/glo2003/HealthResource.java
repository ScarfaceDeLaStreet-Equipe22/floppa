package ulaval.glo2003;

import dev.morphia.Datastore;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.application.repository.ProductMongoRepository;

@Path("/health")
public class HealthResource {


    public HealthResponse response ;
    public HealthResource(Datastore datastore, ProductMongoRepository productMongoRepository) {
        Object sellers = datastore.getDatabase().listCollectionNames().first();

        if (sellers == null){
            this.response = new HealthResponse(true,false);
        }
        this.response = new HealthResponse(true,true);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response health() {

        return Response.ok(response).build();
    }
}
