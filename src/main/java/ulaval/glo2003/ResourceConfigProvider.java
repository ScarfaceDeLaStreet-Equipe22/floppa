package ulaval.glo2003;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.api.exceptions.MissingParamExceptionMapper;
import ulaval.glo2003.api.mappers.*;
import ulaval.glo2003.application.repository.*;
import ulaval.glo2003.domain.exceptions.InvalidParamExceptionMapper;
import ulaval.glo2003.domain.exceptions.ItemNotFoundExceptionMapper;
import ulaval.glo2003.domain.exceptions.NotPermitedExceptionMapper;

import java.util.Optional;

public class ResourceConfigProvider
{

    public ResourceConfig provide()
    {
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
        BuyerMongoRepository buyerMongoRepository = new BuyerMongoRepository(datastore);

        ProductMapper productMapper = new ProductMapper();
        SellerMapper sellerMapper = new SellerMapper();
        OfferMapper offerMapper = new OfferMapper();
        ProductFiltersMapper productFiltersMapper = new ProductFiltersMapper();
        BuyerMapper buyerMapper = new BuyerMapper();

        SellerRessource sellerRessource = new SellerRessource(sellerRepository, sellerMapper, sellerMongoRepository);
        BuyerRessource buyerRessource = new BuyerRessource(buyerMongoRepository, buyerMapper);
        ProductRessource productRessource =
                new ProductRessource(
                        sellerRepository,
                        productRepository,
                        productMapper,
                        offerMapper,
                        productFiltersMapper,
                        sellerMongoRepository,
                        productMongoRepository,
                        buyerMongoRepository);

        return new ResourceConfig()
                .register(new HealthRessource(datastore))
                .register(sellerRessource)
                .register(productRessource)
                .register(buyerRessource)
                .register(new MissingParamExceptionMapper())
                .register(new InvalidParamExceptionMapper())
                .register(new ItemNotFoundExceptionMapper())
                .register(new NotPermitedExceptionMapper())
                .register(JacksonJaxbJsonProvider.class);

    }
}
