package ulaval.glo2003.api.Offer;

import ulaval.glo2003.domain.ProductClasses.Amount;

public class OfferRequest {

    public String amount;
    public String message ;

    public OfferRequest(String amount, String message) {
        this.amount = amount;
        this.message = message;
    }

    public String getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }
}
