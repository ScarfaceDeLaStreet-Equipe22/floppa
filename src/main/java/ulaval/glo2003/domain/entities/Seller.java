package ulaval.glo2003.domain.entities;

import java.util.ArrayList;
import java.util.UUID;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.utils.Date;
import ulaval.glo2003.domain.utils.DateTime;
import ulaval.glo2003.domain.utils.Email;
import ulaval.glo2003.domain.utils.PhoneNumber;


@Entity
public class Seller {

    public String name;
    public String bio;
    public Date birthDate;
    public Email email;
    public PhoneNumber phoneNumber;
    public ArrayList<Product> products;
    public DateTime createdAt;

    @Id
    public String id;

    public Seller(){}

    public Seller(String name, String birthDate, String email, String phoneNumber, String bio) {
        this.name = name;
        this.birthDate = new Date(birthDate);
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.bio = bio;
        this.createdAt = new DateTime();

        this.id = UUID.randomUUID().toString();
        products = new ArrayList<>();
    }

    public Seller( SellerMongoModel sellerMongoModel, ArrayList<Product> productsIds){
        this.name = sellerMongoModel.name;
        this.birthDate = sellerMongoModel.birthDate;
        this.email = new Email(sellerMongoModel.email);
        this.phoneNumber = sellerMongoModel.phoneNumber;
        this.bio =sellerMongoModel.bio;
        this.createdAt = sellerMongoModel.createdAt;

        this.id = sellerMongoModel.id;
        this.products = productsIds;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public Date getBirthdate() {
        return birthDate;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getPhoneNumber() {
        return phoneNumber.getNumero();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
