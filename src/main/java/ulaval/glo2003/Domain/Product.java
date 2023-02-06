package ulaval.glo2003.Domain;

import java.util.UUID;

public class Product {
    public String title ;
    public String description;
    public Amount suggestedPrice;
    public ProductCategory category ;
    public String id;

    public Product(String title, String description, ProductCategory category, Amount suggestedPrice) {
        this.category = category ;
        this.suggestedPrice = suggestedPrice ;
        this.description = description;
        this.title = title ;

        this.id = UUID.randomUUID().toString();
    }

}
