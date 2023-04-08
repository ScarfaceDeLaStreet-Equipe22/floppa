package ulaval.glo2003.domain.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.utils.*;

@Entity("Products")
public class Product {

    public String title;
    public String description;
    public Amount suggestedPrice;
    public ProductCategory category;
    @Id
    public String id;
    public DateTime createdAt;
    public Seller seller;
    public SellerMongoModel sellerMongoModel;
    public ArrayList<Offer> offers;
    public SaleStatus saleStatus;
    public SelectedOffer selectedOffer;

    public Product(){}

    public Product(
            String title,
            String description,
            ProductCategory category,
            Amount suggestedPrice,
            SellerMongoModel sellerMongo) {
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.description = description;
        this.title = title;
        this.createdAt = new DateTime();
        this.id = UUID.randomUUID().toString();
        this.seller = seller;
        this.sellerMongoModel = sellerMongo;
        this.offers = new ArrayList<>();
        this.saleStatus = new SaleStatus();
        this.selectedOffer = new SelectedOffer();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Amount getSuggestedPrice() {
        return suggestedPrice;
    }
    public double getSuggestedPriceDouble() {
        return suggestedPrice.getAmount();
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

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public int getNumberOfOffers() {
        return offers.size();
    }

    public String getSaleStatus() { return saleStatus.getStatus(); }

    public HashMap<String, String> getSlectedOffer(){return selectedOffer.formatForJsonResponse();}
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuggestedPrice(Amount suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public SellerMongoModel getSellerMongoModel() {
        return sellerMongoModel;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public double getAverageAmountOfOffers() {
        if (offers.size() != 0) {
            double total = 0;
            for (Offer offer : offers) {
                total += offer.getAmount();
            }
            return total / offers.size();
        } else {
            return 0;
        }
    }

    public double getMinimumAmountOfOffers() {
        if (offers.size() != 0) {
            double minAmount = Double.MAX_VALUE;
            for (Offer offer : offers) {
                if (offer.getAmount() < minAmount) {
                    minAmount = offer.getAmount();
                }
            }
            return minAmount;
        } else {
            return 0;
        }
    }

    public double getMaximumAmountOfOffers() {
        if (offers.size() != 0) {
            double maxAmount = 0;
            for (Offer offer : offers) {
                if (offer.getAmount() > maxAmount) {
                    maxAmount = offer.getAmount();
                }
            }
            return maxAmount;
        } else {
            return 0;
        }
    }

    public void getSold(String buyerUsername, Double amount){
        this.saleStatus.setSaleStatus("sold");
        this.selectedOffer.acceptedOffer(buyerUsername, amount);
    }
}
