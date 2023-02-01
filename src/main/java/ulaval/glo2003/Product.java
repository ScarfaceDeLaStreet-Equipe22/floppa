package ulaval.glo2003;

public class Product {
    public String title ;

    public String description;
    public double suggestedPrice;
    public String category ;

    public Product(String title, String description, String category, double suggestedPrice) {
        this.category = category ;
        this.suggestedPrice = suggestedPrice ;
        this.description = description;
        this.title = title ;

    }

    public Product(String title, String description, String category) {
        this.category = category ;
        this.suggestedPrice = 1.00 ;
        this.description = description;
        this.title = title ;

    }
//    public ProductCategory category ;
}
