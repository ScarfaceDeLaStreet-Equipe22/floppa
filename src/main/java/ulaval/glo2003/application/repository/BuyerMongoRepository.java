package ulaval.glo2003.application.repository;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.domain.entities.*;

import java.util.ArrayList;

public class BuyerMongoRepository implements IRepository<Buyer> {

    private final Datastore datastore;

    public BuyerMongoRepository(Datastore datastore) {
        this.datastore = datastore;
    }
    @Override
    public void save(Buyer buyer) {
        BuyerMongoModel buyerToSave = new BuyerMongoModel(buyer);


        try {
            datastore.save(buyerToSave);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(Buyer entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(Buyer entity) {

    }

    public Buyer getBuyerByName(String name){
        try {
            BuyerMongoModel model = datastore.find(BuyerMongoModel.class)
                    .filter(Filters.eq("name", name)).iterator().next();

            return new Buyer(model);
        } catch (Exception e) {
            throw new RuntimeException("could not find Buyer because :" + e.getMessage());
        }
    }

    public Buyer getBuyerById(String Id){
        try {
            BuyerMongoModel model = datastore.find(BuyerMongoModel.class)
                    .filter(Filters.eq("_id", Id)).iterator().next();

            return new Buyer(model);
        } catch (Exception e) {
            throw new RuntimeException("could not find Buyer because :" + e.getMessage());
        }
    }


    @Override
    public ArrayList<Buyer> findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }


}
