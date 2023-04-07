package ulaval.glo2003.domain.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import ulaval.glo2003.domain.utils.*;

import java.util.ArrayList;

@Entity("Sellers")
public class SellerMongoModel {
    public String name;
    public String bio;
    public Date birthDate;
    public String email;
    public PhoneNumber phoneNumber;
    public ArrayList<String> productsIds;
    public DateTime createdAt;
    @Id
    public String id;

    public SellerMongoModel() {
    }

    public SellerMongoModel(Seller seller) {
        this.name = seller.name;
        this.bio = seller.getBio();
        this.birthDate = seller.birthDate;
        this.email = seller.getEmail();
        if (seller.getProducts() == null){
            this.productsIds = new ArrayList<String>();
        } else {
            this.productsIds = getIdsOfProducts(seller.getProducts());
        }
        this.phoneNumber = seller.phoneNumber;
        this.createdAt = seller.getCreatedAt();
        this.id = seller.getId();
    }

    public void setName(String name) {
        this.name = name;
    }

//        Object ops = datastore
//                .createUpdateOperations(SellerMongoModel.class)
//                .push("productsIds", idOfProduct);
//        datastore.update(query, ops);

        private ArrayList<String> getIdsOfProducts(ArrayList<Product> productsList){
        ArrayList<String> listOfIds = new ArrayList<>();

        for(Product product : productsList) {
            listOfIds.add(product.getId());
        }

        return listOfIds;
    }

}
