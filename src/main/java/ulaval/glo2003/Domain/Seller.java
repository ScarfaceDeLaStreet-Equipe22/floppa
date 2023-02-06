package ulaval.glo2003.Domain;

import ulaval.glo2003.Domain.Product;

import java.util.ArrayList;
import java.util.UUID;

public class Seller {

    public String name;
    public String bio;
    public String birthDate;
    public Email email;
    public String phoneNumber ;
    public ArrayList<Product> products ;
    private final String id;

    public Seller(String name, String birthDate, String email, String phoneNumber, String bio) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = new Email(email) ;
        this.phoneNumber = phoneNumber ;
        this.bio = bio;

        this.id = UUID.randomUUID().toString();
        products = new ArrayList<>() ;
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
        return email.getEmail();
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
