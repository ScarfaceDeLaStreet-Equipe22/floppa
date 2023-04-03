package ulaval.glo2003.application.repository;

import java.util.ArrayList;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.entities.wesh;

public class SellerMongoRepository implements IRepository<Seller> {

    private final Datastore datastore;


    public SellerMongoRepository(Datastore datastore) {
        this.datastore = datastore;
    }
    @Override
    public void save(Seller seller) {
        ArrayList<String> listOfIds = getIdsOfProducts(seller.getProducts());
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

    @Override
    public ArrayList findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    private ArrayList<String> getIdsOfProducts(ArrayList<Product> productsList){
        ArrayList<String> listOfIds = new ArrayList<>();

        productsList.forEach((product -> {
            listOfIds.add(product.id);
        }));
        return listOfIds;
    }

}
