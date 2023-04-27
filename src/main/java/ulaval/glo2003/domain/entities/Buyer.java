package ulaval.glo2003.domain.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity("Buyers")
public class Buyer {

    public String name;
    public Date birthDate;
    public Email email;
    public PhoneNumber phoneNumber;
    public DateTime createdAt;
    public List<ProductCategory> preferences;

    @Id
    public String id;

    public Buyer(){}

    public Buyer(String name, String birthDate, String email, String phoneNumber, List<ProductCategory> preferences) {
        this.name = name;
        this.birthDate = new Date(birthDate);
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.createdAt = new DateTime();
        this.preferences = preferences;

        this.id = UUID.randomUUID().toString();
    }

    public Buyer(BuyerMongoModel buyerMongoModel){
        this.name = buyerMongoModel.name;
        this.birthDate = buyerMongoModel.birthDate;
        this.email = new Email(buyerMongoModel.email);
        this.phoneNumber = buyerMongoModel.phoneNumber;
        this.createdAt = buyerMongoModel.createdAt;
        this.preferences = buyerMongoModel.preferences;

        this.id = buyerMongoModel.id;
    }

    public String getName() {
        return name;
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

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
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
