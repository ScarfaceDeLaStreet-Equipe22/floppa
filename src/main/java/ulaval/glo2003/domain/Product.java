package ulaval.glo2003.domain;

import java.util.UUID;
import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;
import ulaval.glo2003.domain.SellerClasses.DateTime;

public class Product {
    public String title;
    public String description;
    public Amount suggestedPrice;
    public ProductCategory category;
    public String id;
    public DateTime createdAt;
    public Seller seller ;

    public Product(
            String title, String description, ProductCategory category, Amount suggestedPrice, Seller seller) {
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.description = description;
        this.title = title;
        this.createdAt = new DateTime();
        this.id = UUID.randomUUID().toString();
        this.seller = seller ;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSuggestedPrice() {
        return String.valueOf(suggestedPrice.getAmount());
    }

    public String getCategory() {
        return category.getCategory();
    }

    public String getId() {
        return id;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public Seller getSeller() {
        return seller;
    }
}
