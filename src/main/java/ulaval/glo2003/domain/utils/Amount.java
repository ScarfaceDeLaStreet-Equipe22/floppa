package ulaval.glo2003.domain.utils;

import ulaval.glo2003.api.exceptions.ProductRequestExceptions.MissingSuggestedPriceException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidAmountException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidSuggestedPriceException;

public class Amount {

    private final double amount;

    public Amount(String amount) {
        AmountValidation(amount);
        this.amount = Double.parseDouble(amount);
    }

    private void AmountValidation(String amount) {
        if (amount != null) {
            if (amount.isEmpty()) {
                throw new InvalidSuggestedPriceException();
            } else {
                try {
                    double amountDouble = Double.parseDouble(amount);
                } catch (Exception e) {
                    throw new InvalidAmountException();
                }
            }
        } else {
            throw new MissingSuggestedPriceException();
        }
    }

    public double toDouble() {
        return amount;
    }
}
