package ulaval.glo2003;

public class Produit {
    public String title ;

    public String description;
    public double suggestedPrice;
    public String category ;

    public Produit(String title, String description, double suggestedPrice, String category) {
        this.title = title ;
        this.description = description;
        this.suggestedPrice = suggestedPrice ;
        this.category = category ;
    }
//    public ProductCategory category ;
}
