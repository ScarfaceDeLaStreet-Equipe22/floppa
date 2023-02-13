package ulaval.glo2003.api.Product;

import ulaval.glo2003.domain.Seller;
import ulaval.glo2003.domain.SellerClasses.DateTime;

public class ProductResponse {

    public String title;
    public String description;
    public double suggestedPrice;
    public String category;
    public String id;
    public String createdAt;

    public SellerInProductResponse seller ;
    public OffersInProductsResponse offers ;

    public ProductResponse(
            String title,
            String description,
            String category,
            String suggestedPrice,
            String id,
            DateTime createdAt, Seller seller,int numberOfOffers, Double average) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.suggestedPrice = Double.parseDouble(suggestedPrice);
        this.id = id;
        this.createdAt = createdAt.getDateTime();
        this.seller = new SellerInProductResponse(seller.getId(),seller.getName()) ;
        this.offers = new OffersInProductsResponse(numberOfOffers, average);
    }
}
