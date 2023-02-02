package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.Seller.SellerRessource;
import ulaval.glo2003.Utils.MissingParamExceptionMapper;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws IOException {
        SellerRessource seller = new SellerRessource();

        ResourceConfig resourceConfig = new ResourceConfig()
                .register(seller)
                .register(new MissingParamExceptionMapper());
        URI uri = URI.create("http://localhost:8080/");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
        System.out.println("salut");
    }
}
