package ulaval.glo2003.domain.utils;

import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingAmountException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;

public class Amount {

    private final double amount;

    public Amount(String amount) {
        AmountValidation(amount);
        this.amount = Double.parseDouble(amount);
    }

    private void AmountValidation(String amount) {
        if (amount.isEmpty())
        {
            throw new MissingAmountException();
        }
        try {
            Double.parseDouble(amount);
        }
        catch (Exception e) {
            throw new InvalidAmountException();
        }
    }

    public double toDouble() {
        return amount;
    }
}
