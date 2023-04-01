package ulaval.glo2003.application.repository;

import java.util.ArrayList;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;

public class SellerMongoRepository implements IRepository<Seller> {

    private final Datastore datastore;


    public SellerMongoRepository() {
        MongoClient client = MongoClients.create("mongodb+srv://admin:admin@processus.5gawlpu.mongodb.net/?retryWrites=true&w=majority");
        datastore = Morphia.createDatastore(client, "Processus");
        datastore.getMapper().mapPackage("ulaval.glo2003");
        datastore.ensureIndexes();
    }
    @Override
    public void save(Seller seller) {
        SellerMongoModel sellerToSave = new SellerMongoModel(seller.getName(),
                seller.bio,
                seller.birthDate,
                seller.getEmail(),
                seller.getPhoneNumber(),
                seller.createdAt,
                seller.getId());


        datastore.save(sellerToSave);
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
}
