package ulaval.glo2003.domain;

import ulaval.glo2003.domain.ProductClasses.*;
import ulaval.glo2003.domain.SellerClasses.DateTime;

public class Offer {

    public Amount amount;
    public String message;
    public String buyerUsername;

    public DateTime createdAt;

    public Offer(String amount, String message, String buyerUsername) {
        this.amount = new Amount(amount);
        this.message = message;
        this.buyerUsername = buyerUsername;
        this.createdAt = new DateTime();
    }

    public Double getAmount() {

        return amount.getAmount();
    }

    public String getMessage() {

        return message;
    }

    public String getBuyerUsername() {

        return buyerUsername;
    }
}
