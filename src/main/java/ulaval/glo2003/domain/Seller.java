package ulaval.glo2003.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;

import ulaval.glo2003.api.SellerExceptions.InvalidBirthdateException;
import ulaval.glo2003.domain.SellerClasses.Date;
import ulaval.glo2003.domain.SellerClasses.DateTime;
import ulaval.glo2003.domain.SellerClasses.Email;
import ulaval.glo2003.domain.SellerClasses.PhoneNumber;


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
        assertAge(new Date(birthDate));
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

    public String getBirthDate() {
        return birthDate.getDate();
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

    private void assertAge(Date birthDate) {
        if (birthDate.getYearsBetweenNow() < 18) {
                throw new InvalidBirthdateException();
            }
    }
}
