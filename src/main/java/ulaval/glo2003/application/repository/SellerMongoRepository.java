package ulaval.glo2003.application.repository;

import java.util.ArrayList;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.annotations.Id;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.experimental.filters.Filter;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.entities.wesh;

public class SellerMongoRepository implements IRepository<Seller> {

    private final Datastore datastore;
    Query<Product> query;


    public SellerMongoRepository(Datastore datastore) {
        this.datastore = datastore;
    }
    @Override
    public void save(Seller seller) {
        SellerMongoModel sellerToSave = new SellerMongoModel(seller);


        try {
            datastore.save(sellerToSave);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void save(wesh lol) {
        try {
            datastore.save(lol);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(Seller entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(Seller entity) {

    }

    public Seller getSellerByName(String name){
        try {
            SellerMongoModel model = datastore.find(SellerMongoModel.class)
                    .filter(Filters.eq("name", name)).iterator().next();

            return new Seller(model,getProductsById(model.productsIds));
        } catch (Exception e) {
            throw new RuntimeException("could not find Seller because :" + e.getMessage());
        }
    }

    public Seller getSellerById(String Id){
        try {
            SellerMongoModel model = datastore.find(SellerMongoModel.class)
                    .filter(Filters.eq("_id", Id)).iterator().next();

            return new Seller(model, getProductsById(model.productsIds));
        } catch (Exception e) {
            throw new RuntimeException("could not find Seller because :" + e.getMessage());
        }
    }
    public void addNewProductToSellerMongo(Product product){
        Seller seller = this.getSellerByName("fred");
        // ajouter un produit à son array de produits
    }


    @Override
    public ArrayList findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    private ArrayList<Product> getProductsById(ArrayList<String> productsList){

        ArrayList<Product> listOfProducts = new ArrayList<>();

        if (productsList != null) {
            for (String id : productsList) {
                Product product = datastore.find(Product.class)
                        .filter(Filters.eq("_id", id)).iterator().next();
                listOfProducts.add(product);
            }
        }
        return listOfProducts;
    }

}