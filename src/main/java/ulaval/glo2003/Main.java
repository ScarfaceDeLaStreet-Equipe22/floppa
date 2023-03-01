package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.api.Utils.InvalidParamExceptionMapper;
import ulaval.glo2003.api.Utils.ItemNotFoundExceptionMapper;
import ulaval.glo2003.api.Utils.MissingParamExceptionMapper;
import ulaval.glo2003.api.Utils.NotPermitedExceptionMapper;
import ulaval.glo2003.application.ProductRepository;
import ulaval.glo2003.application.SellerRepository;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.Seller;

public class Main {

    public static void main(String[] args) throws IOException {

        ProductRepository productRepository = new ProductRepository();
        SellerRepository sellerRepository = new SellerRepository();

        // seller and product config
        SellerRessource seller = new SellerRessource(sellerRepository);
        ProductRessource produit = new ProductRessource(sellerRepository, productRepository);

        ResourceConfig resourceConfig = new ResourceConfig().register(new HealthResource());

        URI uri = URI.create("http://localhost:8080/");
        resourceConfig
                .register(seller)
                .register(produit)
                .register(new MissingParamExceptionMapper())
                .register(new InvalidParamExceptionMapper())
                .register(new ItemNotFoundExceptionMapper())
                .register(new NotPermitedExceptionMapper());

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);

        server.start();
    }
}
