package ulaval.glo2003.api.Product;

import ulaval.glo2003.domain.Seller;

public class ProductRequest {


    public String title;
    public String description;
    public String suggestedPrice;
    public String category;

    public ProductRequest() {}
    public ProductRequest(String title, String description, String suggestedPrice, String category) {
        this.suggestedPrice = suggestedPrice ;
        this.description = description ;
        this.category = category ;
        this.title = title ;
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
