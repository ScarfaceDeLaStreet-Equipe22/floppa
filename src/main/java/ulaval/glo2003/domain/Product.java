package ulaval.glo2003.domain;

import java.util.ArrayList;
import java.util.UUID;
import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;
import ulaval.glo2003.domain.SellerClasses.DateTime;

public class Product {
    public String title;
    public String description;
    public Amount suggestedPrice;
    public ProductCategory category;
    public String id;
    public DateTime createdAt;
    public Seller seller ;

    public ArrayList<Offer> offers ;

    public Product(
            String title, String description, ProductCategory category, Amount suggestedPrice, Seller seller) {
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.description = description;
        this.title = title;
        this.createdAt = new DateTime();
        this.id = UUID.randomUUID().toString();
        this.seller = seller ;
        this.offers = new ArrayList<>() ;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSuggestedPrice() {
        return String.valueOf(suggestedPrice.getAmount());
    }

    public String getCategory() {
        return category.getCategory();
    }

    public String getId() {
        return id;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public Seller getSeller() {
        return seller;
    }

    public void addOffer(Offer offer){
        this.offers.add(offer) ;
    }

    public int getNumberOfOffers(){
        return offers.size() ;
    }

    public double getAverageAmountOfOffers(){
        if(offers.size() != 0 ) {
            double total = 0;
            for (Offer offer : offers) {
                total += offer.getAmount();
            }
            return total / offers.size();
        }
        else {
            return 0;
        }
    }
}
