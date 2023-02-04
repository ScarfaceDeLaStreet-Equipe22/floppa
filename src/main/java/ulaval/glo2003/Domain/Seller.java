package ulaval.glo2003.Domain;

import ulaval.glo2003.Domain.Product;

import java.util.ArrayList;
import java.util.UUID;

public class Seller {

    public String name;
    public String bio;
    public String birthDate;
    public String email;
    public String phoneNumber ;
    public ArrayList<Product> products ;
    private final String id;

    public Seller(String name, String bio, String birthDate, String email, String phoneNumber) {
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.email = email ;
        this.phoneNumber = phoneNumber ;
        products = new ArrayList<>() ;

        this.id = UUID.randomUUID().toString();
    }


    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getBirthDate() {
        return birthDate;
    }
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}
