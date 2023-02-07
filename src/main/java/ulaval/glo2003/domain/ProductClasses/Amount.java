package ulaval.glo2003.Domain.ProductClasses;

import ulaval.glo2003.api.ProductExceptions.InvalidSuggestedPriceException;

public class Amount {

    private double amount;

    public Amount(String amount) {
        AmountValidation(amount);
    }

    private void AmountValidation(String amount) {
        try {
            if (amount != null) {
                if (amount.isEmpty()) {
                    this.amount = 1.00;
                } else {
                    Double l = Double.parseDouble(amount);
                    this.amount = l;
                }
            } else {
                this.amount = 1.00;
            }
        } catch (Exception e) {
            throw new InvalidSuggestedPriceException();
        }
    }

    public double getAmount() {
        return amount;
    }
}
