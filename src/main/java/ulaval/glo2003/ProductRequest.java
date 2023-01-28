package ulaval.glo2003;

public class ProductRequest {


    public String description;
    public double suggestedPrice;
    public String category ;
    public String title ;
    public ProductRequest() {
    }

    public boolean isEmpty() {
        if (this.title.isEmpty() && this.description.isEmpty() && this.category.isEmpty()){
            return true ;
        } return false ;
    }
}
