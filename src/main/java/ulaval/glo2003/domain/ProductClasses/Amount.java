package ulaval.glo2003.domain.ProductClasses;

import ulaval.glo2003.api.ProductExceptions.InvalidAmountException;
import ulaval.glo2003.api.ProductExceptions.InvalidSuggestedPriceException;
import ulaval.glo2003.api.ProductExceptions.MissingSuggestedPriceException;

public class Amount {

    private double amount;

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

    public double getAmount() {
        return amount;
    }
}
