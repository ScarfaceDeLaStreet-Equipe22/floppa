package ulaval.glo2003;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
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
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.exceptions.InvalidParamExceptionMapper;
import ulaval.glo2003.domain.exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.domain.exceptions.NotPermitedExceptionMapper;

import static dev.morphia.query.experimental.filters.Filters.gt;

public class Main {

    public static void main(String[] args) throws IOException {
        Datastore datastore;




        String FLOPPA_MONGO_CLUSTER_URL = "mongodb+srv://admin:admin@processus.5gawlpu.mongodb.net/?retryWrites=true&w=majority&connectTimeoutMS=10000";
        String FLOPPA_MONGO_DATABASE = "Processus";

        MongoClient client = MongoClients.create(FLOPPA_MONGO_CLUSTER_URL);
        datastore = Morphia.createDatastore(client, FLOPPA_MONGO_DATABASE);
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();


        Object products = datastore.getDatabase().listCollectionNames().first();

        // configuration des repository
        ProductRepository productRepository = new ProductRepository();
        SellerRepository sellerRepository = new SellerRepository();
        SellerMongoRepository sellerMongoRepository = new SellerMongoRepository(datastore);
        ProductMongoRepository productMongoRepository = new ProductMongoRepository(datastore, sellerMongoRepository);


//        Seller seller = sellerMongoRepository.getSellerByName("fred");

        // configuration des mappers
        ProductMapper productMapper = new ProductMapper();
        SellerMapper sellerMapper = new SellerMapper();
        OfferMapper offerMapper = new OfferMapper();
        ProductFiltersMapper productFiltersMapper = new ProductFiltersMapper();

        // seller and product config
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
