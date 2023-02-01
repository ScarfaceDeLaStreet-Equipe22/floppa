package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws IOException {
        ResourceConfig resourceConfig = new ResourceConfig();
        CreationProduit produit = new CreationProduit() ;
        MissingParameterException missingParameterException = new MissingParameterException() ;
        IllegalParameterException illegalParameterException = new IllegalParameterException();

        URI uri = URI.create("http://localhost:8080/");
        resourceConfig.register(produit)
                .register(missingParameterException)
                .register(illegalParameterException);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
        System.out.println("salut");
    }
}
