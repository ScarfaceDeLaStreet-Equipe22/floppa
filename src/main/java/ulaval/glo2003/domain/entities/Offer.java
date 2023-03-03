package ulaval.glo2003.domain.entities;

import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.DateTime;

public class Offer {

    public Amount amount;
    public String message;
    public String buyerUsername;
    public DateTime createdAt;

    public Offer(Amount amount, String message, String buyerUsername) {
        this.amount = amount;
        this.message = message;
        this.buyerUsername = buyerUsername;
        this.createdAt = new DateTime();
    }

    public double getAmount() {
        return amount.toDouble();
    }

    public String getMessage() {
        return message;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }
}
