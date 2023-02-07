package ulaval.glo2003.api.Product;

import ulaval.glo2003.Domain.SellerClasses.DateTime;

public class ProductResponse {

    public String title;
    public String description;
    public double suggestedPrice;
    public String category ;
    public String id;
    public String createdAt ;

    public ProductResponse(String title, String description, String category, String suggestedPrice, String id, DateTime createdAt) {
        this.title = title ;
        this.description = description ;
        this.category = category ;
        this.suggestedPrice = Double.parseDouble(suggestedPrice);
        this.id = id;
        this.createdAt = createdAt.getDateTime() ;
    }

}
