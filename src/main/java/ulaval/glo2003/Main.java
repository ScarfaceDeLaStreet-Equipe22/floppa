package ulaval.glo2003;
import ulaval.glo2003.Product.*;
import ulaval.glo2003.Utils.*;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws IOException {
        ResourceConfig resourceConfig = new ResourceConfig();
        CreationProduit produit = new CreationProduit() ;
        IllegalParameterException illegalParameterException = new IllegalParameterException();
//
//=======
//        SellerFactory sellerFactory = new SellerFactory();
//
//        ResourceConfig resourceConfig = new ResourceConfig()
//                .register(new SellerRessource(sellerFactory))
//                .register(new MissingParamExceptionMapper());
//>>>>>>> blabla
        URI uri = URI.create("http://localhost:8080/");
        resourceConfig.register(produit)
                .register(new MissingParamExceptionMapper());

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
        System.out.println("salut");
    }
}
