package ulaval.glo2003.application.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.domain.entities.*;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;

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
        try {
            Product model = datastore.find(Product.class)
                    .filter(Filters.eq("_id", entity.getId())).iterator().next();
            datastore.delete(model);
        } catch (Exception e) {
            throw new ItemNotFoundException("could not find Product because :" + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        datastore.getDatabase().getCollection("Products").drop();
    }

    @Override
    public void update(Product entity) {
        try {
            Product model = datastore.find(Product.class)
                    .filter(Filters.eq("_id", entity.getId())).iterator().next();
            datastore.save(entity);
        } catch (Exception e) {
            throw new ItemNotFoundException("could not find Product because :" + e.getMessage());
        }
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
        int count ;
        List<Product> model = datastore.find(Product.class)
                .iterator().toList();

        return model.size();
    }


    public Product findById(String id) {
        Product product;
        try {
             product = datastore.find(Product.class)
                    .filter(Filters.eq("_id", id)).iterator().next();
            if (product.offers == null) {
                product.offers = new ArrayList<Offer>();
            }
        } catch (Exception e) {
            throw new ItemNotFoundException("Product not found");
        }
        return product;
    }
}
