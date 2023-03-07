package ulaval.glo2003.domain.entities;

import java.util.ArrayList;
import java.util.UUID;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.DateTime;
import ulaval.glo2003.domain.utils.ProductCategory;

public class Product {
    public String title;
    public String description;
    public Amount suggestedPrice;
    public ProductCategory category;

    public String id;
    public DateTime createdAt;

    public Seller seller;
    public ArrayList<Offer> offers;

    public Product(
            String title,
            String description,
            ProductCategory category,
            Amount suggestedPrice,
            Seller seller) {
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.description = description;
        this.title = title;
        this.createdAt = new DateTime();
        this.id = UUID.randomUUID().toString();
        this.seller = seller;
        this.offers = new ArrayList<>();
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

    public ArrayList<Offer> getOffers() { return offers; }

    public int getNumberOfOffers() {
        return offers.size();
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
            double minAmount = 0;
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
}
