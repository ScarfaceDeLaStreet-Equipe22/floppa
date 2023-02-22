package ulaval.glo2003.domain.ProductClasses;

import ulaval.glo2003.api.ProductExceptions.InvalidSuggestedPriceException;
import ulaval.glo2003.api.ProductExceptions.MissingSuggestedPriceException;

public class Amount {

    private double amount;

    public Amount(String amount) {
        AmountValidation(amount);
    }

    private void AmountValidation(String amount) {
//        try {
            if (amount != null) {
                if (amount.isEmpty()) {
                    throw new InvalidSuggestedPriceException();
                } else {
                    double amountDouble =  Double.parseDouble(amount);
                    if (amountDouble < 1){
                        throw new InvalidSuggestedPriceException() ;
                    } else {
                        this.amount = amountDouble ;
                    }
                }
            } else {
                throw new MissingSuggestedPriceException() ;
//            }
//        } catch (Exception e) {
//            throw new InvalidSuggestedPriceException();
//        }
    }
    }

    public double getAmount() {
        return amount;
    }
}
