package ulaval.glo2003.api.responses;

import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.utils.DateTime;

import java.util.HashMap;

public class ProductResponse {

    public String title;
    public String description;
    public double suggestedPrice;
    public String category;
    public String id;
    public String createdAt;
    public SellerInProductResponse seller;
    public OffersInProductsResponse offers;
    public String saleStatus;
    public HashMap<String, String> selectedOffer;

    public ProductResponse(
            String title,
            String description,
            String category,
            double suggestedPrice,
            String id,
            DateTime createdAt,
            Seller seller,
            OffersInProductsResponse offers,
            String saleStatus,
            HashMap<String, String> selectedOffer) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.id = id;
        this.createdAt = createdAt.getDateTime();
        this.seller = new SellerInProductResponse(seller.getId(), seller.getName());
        this.offers = offers;
        this.saleStatus = saleStatus;
        this.selectedOffer = selectedOffer;
    }

    public ProductResponse(
            String title,
            String description,
            String category,
            double suggestedPrice,
            String id,
            DateTime createdAt,
            OffersInProductsResponse offers,
            String saleStatus,
            HashMap<String, String> selectedOffer) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.id = id;
        this.createdAt = createdAt.getDateTime();
        this.offers = offers;
        this.saleStatus = saleStatus;
        this.selectedOffer = selectedOffer;
    }

    public ProductResponse() {}
}
