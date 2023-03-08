package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.api.exceptions.MissingParamExceptionMapper;
import ulaval.glo2003.api.mappers.OfferMapper;
import ulaval.glo2003.api.mappers.ProductFiltersMapper;
import ulaval.glo2003.api.mappers.ProductMapper;
import ulaval.glo2003.api.mappers.SellerMapper;
import ulaval.glo2003.application.repository.ProductRepository;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.exceptions.InvalidParamExceptionMapper;
import ulaval.glo2003.domain.exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.domain.exceptions.NotPermitedExceptionMapper;

public class Main {

    public static void main(String[] args) throws IOException {

        // configuration des repository
        ProductRepository productRepository = new ProductRepository();
        SellerRepository sellerRepository = new SellerRepository();

        // configuration des mappers
        ProductMapper productMapper = new ProductMapper();
        SellerMapper sellerMapper = new SellerMapper();
        OfferMapper offerMapper = new OfferMapper();
        ProductFiltersMapper productFiltersMapper = new ProductFiltersMapper();

        // seller and product config
        SellerRessource seller = new SellerRessource(sellerRepository, sellerMapper);
        ProductRessource produit =
                new ProductRessource(
                        sellerRepository,
                        productRepository,
                        productMapper,
                        offerMapper,
                        productFiltersMapper);

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
