package ulaval.glo2003.application.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;

import java.util.ArrayList;
import java.util.List;


public class ProductMongoRepository implements IRepository<Product>{
    private Datastore datastore;
    SellerMongoRepository sellerMongoRepository;

    public ProductMongoRepository(Datastore datastore, SellerMongoRepository sellerMongoRepository) {
        this.sellerMongoRepository = sellerMongoRepository;
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

    public List<Product> getAllProducts(){
        List<Product> products = datastore.find(Product.class).iterator().toList();

        products.forEach(product -> {
            if(product.seller == null){
                product.seller = new Seller(product.getSellerMongoModel(), sellerMongoRepository.getProductsById(product.getSellerMongoModel().productsIds));
            }
        });
        return products;
    }

    @Override
    public int count() {
        return 0;
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
