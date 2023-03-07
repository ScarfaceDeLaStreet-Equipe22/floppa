package ulaval.glo2003.api.requests;

import ulaval.glo2003.domain.entities.Seller;

public class ProductRequest {

    public String title;
    public String description;
    public String suggestedPrice;
    public String category;

    public ProductRequest() {}
    public ProductRequest(String title, String description, String category, String suggestedPrice) {
        this.title = title;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSuggestedPrice() {
        return suggestedPrice;
    }

    public String getCategory() {
        return category;
    }
}
