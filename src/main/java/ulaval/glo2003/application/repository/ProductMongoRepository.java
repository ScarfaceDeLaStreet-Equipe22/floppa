package ulaval.glo2003.application.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.ProductMongoModel;
import ulaval.glo2003.domain.entities.SellerMongoModel;

import java.util.ArrayList;

public class ProductMongoRepository implements IRepository<Product>{
    private final Datastore datastore;

    public ProductMongoRepository(Datastore datastore) {
        this.datastore = datastore;
        MongoClient client = MongoClients.create("mongodb+srv://admin:admin@processus.5gawlpu.mongodb.net/?retryWrites=true&w=majority");
        datastore = Morphia.createDatastore(client, "Processus");
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();
    }

    @Override
    public void save(Product product) {
        datastore.save(product);
    }

    @Override
    public void remove(Product entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public ArrayList<Product> findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    private ArrayList<String> getIdsOfOffers(ArrayList<Offer> offersList){
        ArrayList<String> idsList = new ArrayList<>();
        offersList.forEach((offer -> {
            idsList.add(offer.id);
        }));
        return idsList;
    }

    public Product findById(String id) {
        Product product = datastore.find(Product.class)
                .filter(Filters.eq("_id", id)).iterator().next();
        if(product.offers == null){
            product.offers = new ArrayList<Offer>();
        }

        return product;
    }
}