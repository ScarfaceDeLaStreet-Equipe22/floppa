package ulaval.glo2003.domain.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.DateTime;

import java.util.UUID;

@Entity
public class Offer {

    public Amount amount;
    public String message;
    public String buyerUsername;
    public DateTime createdAt;
    @Id
    public String id;


    public Offer(){}
    public Offer(Amount amount, String message, String buyerUsername) {
        this.amount = amount;
        this.message = message;
        this.buyerUsername = buyerUsername;
        this.createdAt = new DateTime();
        this.id = UUID.randomUUID().toString();
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

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
