package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.api.Utils.InvalidParamExceptionMapper;
import ulaval.glo2003.api.Utils.ItemNotFoundExceptionMapper;
import ulaval.glo2003.api.Utils.MissingParamExceptionMapper;
import ulaval.glo2003.HealthResource;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Seller> sellers = new ArrayList<>();

        // seller and product config
        SellerRessource seller = new SellerRessource(sellers);
        ProductRessource produit = new ProductRessource(sellers);

        ResourceConfig resourceConfig = new ResourceConfig().register(new HealthResource());

        URI uri = URI.create("http://localhost:8080/");
        resourceConfig
                .register(seller)
                .register(produit)
                .register(new MissingParamExceptionMapper())
                .register(new InvalidParamExceptionMapper())
                .register(new ItemNotFoundExceptionMapper());

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }
}
