package ulaval.glo2003.domain;

import java.util.UUID;

import ulaval.glo2003.api.ProductExceptions.InvalidSuggestedPriceException;
import ulaval.glo2003.application.OfferRepository;
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
    public Seller seller;

    public OfferRepository offerRepository;

    public Product(String title, String description, ProductCategory category, Amount suggestedPrice, Seller seller) {
        assertSuggestedPrice(suggestedPrice);
        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.description = description;
        this.title = title;
        this.createdAt = new DateTime();
        this.id = UUID.randomUUID().toString();
        this.seller = seller;
        this.offerRepository = new OfferRepository();
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

    public int getNumberOfOffers() {
        return offerRepository.findAll().size();
    }

    public double getAverageAmountOfOffers() {
        if (offerRepository.findAll().size() != 0) {
            double total = 0;
            for (Offer offer : offerRepository.findAll()) {
                total += Double.parseDouble(offer.getAmount());
            }
            return total / offerRepository.findAll().size();
        } else {
            return 0;
        }
    }

    private void assertSuggestedPrice(Amount suggestedPrice){
        if(suggestedPrice.getAmount() < 1)
        {
            throw new InvalidSuggestedPriceException();
        }
    }
}
