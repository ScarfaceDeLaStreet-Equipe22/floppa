package ulaval.glo2003;

import dev.morphia.Datastore;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import netscape.javascript.JSObject;
import ulaval.glo2003.application.repository.ProductMongoRepository;
import ulaval.glo2003.application.repository.ProductRepository;
import ulaval.glo2003.domain.entities.Product;

import java.util.List;

@Path("/health")
public class HealthResource {


    public HealthResonse response ;
    public HealthResource(Datastore datastore, ProductMongoRepository productMongoRepository) {
        Object sellers = datastore.getDatabase().listCollectionNames().first();

        if (sellers == null){
            this.response = new HealthResonse(true,false);
        }
        this.response = new HealthResonse(true,true);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response health() {

        return Response.ok(response).build();
    }
}
