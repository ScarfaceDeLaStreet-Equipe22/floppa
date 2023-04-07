package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.api.exceptions.MissingParamExceptionMapper;
import ulaval.glo2003.api.mappers.OfferMapper;
import ulaval.glo2003.api.mappers.ProductFiltersMapper;
import ulaval.glo2003.api.mappers.ProductMapper;
import ulaval.glo2003.api.mappers.SellerMapper;
import ulaval.glo2003.application.repository.ProductMongoRepository;
import ulaval.glo2003.application.repository.ProductRepository;
import ulaval.glo2003.application.repository.SellerMongoRepository;
import ulaval.glo2003.application.repository.SellerRepository;
import ulaval.glo2003.domain.exceptions.InvalidParamExceptionMapper;
import ulaval.glo2003.domain.exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.domain.exceptions.NotPermitedExceptionMapper;

public class Main {

    public static void main(String[] args) throws IOException {
        Datastore datastore;


        String MONGO_CLUSTER_LINK = Optional.ofNullable(System.getenv("FLOPPA_MONGO_CLUSTER_URL")).orElse("mongodb+srv://admin:admin@processus.5gawlpu.mongodb.net/?retryWrites=true&w=majority&connectTimeoutMS=10000");
        String MONGO_NAME = Optional.ofNullable(System.getenv("FLOPPA_MONGO_DATABASE")).orElse("Processus");

        MongoClient client = MongoClients.create(MONGO_CLUSTER_LINK);
        datastore = Morphia.createDatastore(client, MONGO_NAME);
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();


        ProductRepository productRepository = new ProductRepository();
        SellerRepository sellerRepository = new SellerRepository();
        SellerMongoRepository sellerMongoRepository = new SellerMongoRepository(datastore);
        ProductMongoRepository productMongoRepository = new ProductMongoRepository(datastore, sellerMongoRepository);

        ProductMapper productMapper = new ProductMapper();
        SellerMapper sellerMapper = new SellerMapper();
        OfferMapper offerMapper = new OfferMapper();
        ProductFiltersMapper productFiltersMapper = new ProductFiltersMapper();

        SellerRessource seller = new SellerRessource(sellerRepository, sellerMapper, sellerMongoRepository);
        ProductRessource produit =
                new ProductRessource(
                        sellerRepository,
                        productRepository,
                        productMapper,
                        offerMapper,
                        productFiltersMapper,
                        sellerMongoRepository,
                        productMongoRepository);

        ResourceConfig resourceConfig = new ResourceConfig().register(new HealthResource(datastore, productMongoRepository));

        URI uri = URI.create("http://0.0.0.0:8080/");
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
