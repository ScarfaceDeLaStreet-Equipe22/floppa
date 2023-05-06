package ulaval.glo2003.application.repository;

import java.util.ArrayList;
import java.util.List;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.domain.entities.*;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;

public class SellerMongoRepository implements IRepository<Seller> {

    public final Datastore datastore;



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

    @Override
    public void remove(Seller entity) {
        try {
            SellerMongoModel model = datastore.find(SellerMongoModel.class)
                    .filter(Filters.eq("_id", entity.getId())).iterator().next();
            datastore.delete(model);
        } catch (Exception e) {
            throw new ItemNotFoundException("could not find Seller because :" + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        datastore.getDatabase().getCollection("Sellers").drop();
    }

    @Override
    public void update(Seller entity) {
        try {
            SellerMongoModel model = datastore.find(SellerMongoModel.class)
                    .filter(Filters.eq("_id", entity.getId())).iterator().next();
            SellerMongoModel sellerToSave = new SellerMongoModel(entity);
            datastore.save(sellerToSave);
        } catch (Exception e) {
            throw new ItemNotFoundException("could not find Seller because :" + e.getMessage());
        }
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
    public int getCount(){
        int count ;
        List<SellerMongoModel> model = datastore.find(SellerMongoModel.class)
                .iterator().toList();

        return model.size();
    }

    public Seller getSellerById(String Id){
        try {
            SellerMongoModel model = datastore.find(SellerMongoModel.class)
                    .filter(Filters.eq("_id", Id)).iterator().next();

            return new Seller(model, getProductsById(model.productsIds));
        } catch (Exception e) {
            throw new ItemNotFoundException("could not find Seller because :" + e.getMessage());
        }
    }


    @Override
    public ArrayList<Seller> findAll() {
        List<SellerMongoModel> model = datastore.find(SellerMongoModel.class)
                .iterator().toList();

        ArrayList<Seller> listeOfAllSellers = new ArrayList<>();
        for (int i = 0; i < model.size() ; i++){
            listeOfAllSellers.add(new Seller(model.get(i), getProductsById(model.get(i).productsIds)));
        }
        return listeOfAllSellers;
    }

    @Override
    public int count() {
        return 0;
    }

    public ArrayList<Product> getProductsById(ArrayList<String> productsList){

        ArrayList<Product> listOfProducts = new ArrayList<>();

        if (productsList != null) {
            for (String id : productsList) {
                Product product = datastore.find(Product.class)
                        .filter(Filters.eq("_id", id)).iterator().next();
                if(product.getOffers() == null){
                    product.offers = new ArrayList<Offer>();
                }
                listOfProducts.add(product);
            }
        }
        return listOfProducts;
    }

}
