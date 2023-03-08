package ulaval.glo2003.api.responses;

import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.utils.DateTime;

public class ProductResponse {

    public String title;
    public String description;
    public double suggestedPrice;
    public String category;
    public String id;
    public String createdAt;
    public SellerInProductResponse seller;
    public OffersInProductsResponse offers;

    public ProductResponse(
            String title,
            String description,
            String category,
            double suggestedPrice,
            String id,
            DateTime createdAt,
            Seller seller,
            OffersInProductsResponse offers) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.id = id;
        this.createdAt = createdAt.getDateTime();
        this.seller = new SellerInProductResponse(seller.getId(), seller.getName());
        this.offers = offers;
    }

    public ProductResponse(
            String title,
            String description,
            String category,
            double suggestedPrice,
            String id,
            DateTime createdAt,
            OffersInProductsResponse offers) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.id = id;
        this.createdAt = createdAt.getDateTime();
        this.offers = offers;
    }
}
