package ulaval.glo2003.domain.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.utils.Date;
import ulaval.glo2003.domain.utils.DateTime;
import ulaval.glo2003.domain.utils.PhoneNumber;
import ulaval.glo2003.domain.utils.ProductCategory;

import java.util.List;

@Entity("Buyers")
public class BuyerMongoModel {
    public String name;
    public Date birthDate;
    public String email;
    public PhoneNumber phoneNumber;
    public DateTime createdAt;
    public List<ProductCategory> preferences;
    @Id
    public String id;

    public BuyerMongoModel() {
    }

    public BuyerMongoModel(Buyer buyer) {
        this.name = buyer.name;
        this.birthDate = buyer.birthDate;
        this.email = buyer.getEmail();
        this.phoneNumber = buyer.phoneNumber;
        this.createdAt = buyer.getCreatedAt();
        this.preferences = buyer.preferences;
        this.id = buyer.getId();
    }

    public void setName(String name) {
        this.name = name;
    }

}
