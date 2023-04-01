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

    public DateTime createdAt;
    @Id
    public String id;

    public SellerMongoModel(String name, String bio, Date birthDate, String email, String phoneNumber,DateTime createdAt, String id) {
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.id = id;
    }
}
