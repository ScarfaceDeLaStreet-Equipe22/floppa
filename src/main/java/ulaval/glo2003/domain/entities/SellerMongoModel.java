package ulaval.glo2003.domain.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.utils.Date;
import ulaval.glo2003.domain.utils.DateTime;
import ulaval.glo2003.domain.utils.Email;
import ulaval.glo2003.domain.utils.PhoneNumber;

import java.util.ArrayList;

@Entity("Sellers")
public class SellerMongoModel {
    public String name;
    public String bio;
    public Date birthDate;
    public String email;
    public String phoneNumber;

    ArrayList<String> productsIds;
    public DateTime createdAt;
    @Id
    public String id;

//    public SellerMongoModel(String name, String bio, Date birthDate, String email, PhoneNumber phoneNumber,ArrayList<String> products,DateTime createdAt, String id) {
//        this.name = name;
//        this.bio = bio;
//        this.birthDate = birthDate;
//        this.email = email;
//        this.productsIds = products;
//        this.phoneNumber = phoneNumber;
//        this.createdAt = createdAt;
//        this.id = id;
//    }
    public SellerMongoModel(Seller seller) {
        this.name = seller.name;
        this.bio = seller.getBio();
        this.birthDate = seller.birthDate;
        this.email = seller.getEmail();
//        this.productsIds = getIdsOfProducts(seller.getProducts());
        this.productsIds = new ArrayList<String>();
        productsIds.add("wewe");
        this.phoneNumber = seller.getPhoneNumber();
        this.createdAt = seller.getCreatedAt();
        this.id = seller.getId();
    }

//    private ArrayList<String> getIdsOfProducts(ArrayList<Product> productsList){
//        ArrayList<String> listOfIds = new ArrayList<>();
//
//        productsList.forEach((product -> {
//            listOfIds.add(product.id);
//        }));
//        return listOfIds;
//    }

}
