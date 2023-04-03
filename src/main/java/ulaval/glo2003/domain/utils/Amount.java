package ulaval.glo2003.domain.utils;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingAmountException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;

import java.util.UUID;

@Entity
public class Amount {

    private final double amount;
    @Id
    public String id;

    public Amount(String amount) {
        AmountValidation(amount);
        this.amount = Double.parseDouble(amount);
        this.id = UUID.randomUUID().toString();
    }

    public double getAmount(){
        return amount;
    }

    private void AmountValidation(String amount) {
        if (amount.isEmpty()) {
            throw new MissingAmountException();
        }
        try {
            Double.parseDouble(amount);
        } catch (Exception e) {
            throw new InvalidAmountException();
        }
    }

    public double toDouble() {
        return amount;
    }
}
