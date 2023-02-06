package ulaval.glo2003.api.Seller;

import ulaval.glo2003.Domain.Email;
import ulaval.glo2003.Domain.Product;

import java.time.Instant;
import java.util.ArrayList;

public class SellerResponse {
    public String name;
    public String bio;
    public String birthDate;
    public String email;
    public String phoneNumber ;

    public Instant createdAt;
    public ArrayList<Product> products ;
    public String id ;


    public SellerResponse(
            String id,
            String name,
            String bio,
            String birthDate,
            String email,
            String phoneNumber,
            ArrayList<Product> products
    ) {
        this.id = id ;
        this.name = name ;
        this.birthDate = birthDate ;
        this.email = email;
        this.phoneNumber = phoneNumber ;
        this.bio = bio ;
        this.products = products ;
    }

}
