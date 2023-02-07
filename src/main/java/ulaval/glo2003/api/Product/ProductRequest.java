package ulaval.glo2003.api.Product;

public class ProductRequest {

    public String title;
    public String description;
    public String suggestedPrice;
    public String category;

    public ProductRequest() {}

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
