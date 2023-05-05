package ulaval.glo2003.domain.utils;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingAmountException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Amount {

    public double amount;
    @Id
    public String id;


    public Amount(){};
    public Amount(String amount) {
        AmountValidation(amount);
        this.amount = Double.parseDouble(amount);
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return Double.compare(amount1.amount, amount) == 0 && Objects.equals(id, amount1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, id);
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
