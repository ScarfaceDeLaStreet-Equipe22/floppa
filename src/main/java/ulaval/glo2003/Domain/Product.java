package ulaval.glo2003.Domain;

public class Product {
    public String title ;

    public String description;
    public Amount suggestedPrice;
    public ProductCategory category ;

    public Product(String title, String description, ProductCategory category, Amount suggestedPrice) {
        this.category = category ;
        this.suggestedPrice = suggestedPrice ;
        this.description = description;
        this.title = title ;
    }
}
