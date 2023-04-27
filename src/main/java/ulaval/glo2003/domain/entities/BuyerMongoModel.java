package ulaval.glo2003.domain.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.utils.Date;
import ulaval.glo2003.domain.utils.DateTime;
import ulaval.glo2003.domain.utils.PhoneNumber;

import java.util.ArrayList;

@Entity("Buyers")
public class BuyerMongoModel {
    public String name;
    public Date birthDate;
    public String email;
    public PhoneNumber phoneNumber;
    public DateTime createdAt;
    @Id
    public String id;

    public BuyerMongoModel() {
    }

    public BuyerMongoModel(Seller seller) {
        this.name = seller.name;
        this.birthDate = seller.birthDate;
        this.email = seller.getEmail();
        this.phoneNumber = seller.phoneNumber;
        this.createdAt = seller.getCreatedAt();
        this.id = seller.getId();
    }

    public void setName(String name) {
        this.name = name;
    }

}
