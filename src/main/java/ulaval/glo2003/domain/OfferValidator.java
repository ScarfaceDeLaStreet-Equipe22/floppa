package ulaval.glo2003.domain;

import ulaval.glo2003.api.OfferExceptions.MissingAmountException;
import ulaval.glo2003.api.OfferExceptions.MissingBuyerUsername;
import ulaval.glo2003.api.OfferExceptions.MissingMessageException;
import ulaval.glo2003.api.ProductExceptions.*;
import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;

public class OfferValidator {

    public Amount amount;
    public String message;
    public String buyerUsername;

    public OfferValidator(String amount, String message, String buyerUsername) {
        assertParamNotNull(amount, message, buyerUsername);
        assertParamNotEmpty(amount, message, buyerUsername);
        this.amount = new Amount(amount);
        this.message = message;
        this.buyerUsername = buyerUsername;
    }

    public String getAmount() {
        return String.valueOf(amount);
    }

    public String getMessage() {

        return message;
    }

    public String getBuyerUsername() {

        return buyerUsername;
    }

    private void assertParamNotNull(String amount, String message, String buyerUsername) {
        if (amount == null) {
            throw new MissingAmountException();
        }
        if (message == null) {
            throw new MissingMessageException();
        }
        if (buyerUsername == null) {
            throw new MissingBuyerUsername(); //a voir
        }

    }

    private void assertParamNotEmpty(String amount, String message,String buyerUsername) {

        if (amount.isEmpty()) {
            throw new InvalidTitleException();
        }
        if (message.isEmpty()) {
            throw new InvalidDescriptionException();
        }
        if (buyerUsername.isEmpty()) {
            throw new InvalidDescriptionException(); // a voir
        }
    }
}
