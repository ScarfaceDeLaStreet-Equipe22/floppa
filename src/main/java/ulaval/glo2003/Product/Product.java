package ulaval.glo2003.Product;

import ulaval.glo2003.Product.Errors.MissingTitleException;
import ulaval.glo2003.Seller.MissingBioException;
import ulaval.glo2003.Seller.MissingBirthDateException;
import ulaval.glo2003.Utils.MissingNameException;

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
