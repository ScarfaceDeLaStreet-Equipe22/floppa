package ulaval.glo2003.domain.entities;

import java.util.ArrayList;
import java.util.UUID;
import ulaval.glo2003.domain.utils.Date;
import ulaval.glo2003.domain.utils.DateTime;
import ulaval.glo2003.domain.utils.Email;
import ulaval.glo2003.domain.utils.PhoneNumber;

public class Seller {

    public String name;
    public String bio;
    public Date birthDate;
    public Email email;
    public PhoneNumber phoneNumber;
    public ArrayList<Product> products;
    public DateTime createdAt;
    private final String id;

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

    public void sellProduct(Product product){
        this.products.remove(product);
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
