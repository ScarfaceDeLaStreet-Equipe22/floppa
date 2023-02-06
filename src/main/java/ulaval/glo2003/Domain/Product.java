package ulaval.glo2003.Domain;

import java.util.UUID;

public class Product {
    public String title ;
    public String description;
    public double suggestedPrice;
    public ProductCategory category ;
    public String id;

    public Product(String title, String description, ProductCategory category, double suggestedPrice) {
        this.category = category ;
        this.suggestedPrice = suggestedPrice ;
        this.description = description;
        this.title = title ;

        this.id = UUID.randomUUID().toString();
    }

    public Product(String title, String description, ProductCategory category, String id) {
        this.category = category ;
        this.id = id;
        this.suggestedPrice = 1.00 ;
        this.description = description;
        this.title = title ;

    }


//    public ProductCategory category ;
}
