package ulaval.glo2003.domain.entities;

import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.utils.DateTime;

import java.util.ArrayList;

public class ProductMongoModel {

    public String title;
    public String description;
    public double suggestedPrice;
    public String category;
    @Id
    public String id;
    public DateTime createdAt;
    public SellerMongoModel seller;
    public ArrayList<String> offersIds;

    public ProductMongoModel(String title, String description, double suggestedPrice, String category, String id, DateTime createdAt, SellerMongoModel seller, ArrayList<String> offersIds) {
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.category = category;
        this.id = id;
        this.createdAt = createdAt;
        this.seller = seller;
        this.offersIds = offersIds;
    }
}
