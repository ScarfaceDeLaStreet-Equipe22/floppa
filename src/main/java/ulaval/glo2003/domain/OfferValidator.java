package ulaval.glo2003.domain;

import ulaval.glo2003.api.OfferExceptions.MissingAmountException;
import ulaval.glo2003.api.OfferExceptions.MissingMessageException;
import ulaval.glo2003.api.OfferExceptions.NotPermittedException;
import ulaval.glo2003.api.ProductExceptions.*;
import ulaval.glo2003.domain.ProductClasses.Amount;

public class OfferValidator {

    public Product product;

    public Amount amount;
    public String message;
    public String buyerUsername;

    public OfferValidator(String amount, String message, String buyerUsername, Product product) {
        assertParamNotNull(amount, message);
        assertParamNotEmpty(amount, message, buyerUsername);
        ValidateMinimumAmount(product, amount);
        ValidateIfBuyerAlreadyMadeOfferOnProduct(product, buyerUsername);
        this.amount = new Amount(amount);
        this.message = message;
        this.buyerUsername = buyerUsername;
        this.product = product;
    }

    public String getAmount() {
        return String.valueOf(amount.getAmount());
    }

    public String getMessage() {
        return message;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    private void assertParamNotNull(String amount, String message) {
        if (amount == null) {
            throw new MissingAmountException();
        }
        if (message == null) {
            throw new MissingMessageException();
        }
    }

    private void assertParamNotEmpty(String amount, String message, String buyerUsername) {
        if (amount.isEmpty()) {
            throw new InvalidTitleException();
        }
        if (message.length() < 100) {
            throw new InvalidDescriptionException();
        }
        if (buyerUsername.isEmpty()) {
            throw new InvalidDescriptionException();
        }
    }

    private void ValidateMinimumAmount(Product product, String amount) {
        Double suggestedPrice = new Amount(product.getSuggestedPrice()).getAmount();
        Double offerAmount = new Amount(amount).getAmount();

        if (offerAmount < suggestedPrice) {
            throw new InvalidAmountException();
        }
    }

    private void ValidateIfBuyerAlreadyMadeOfferOnProduct(Product product, String buyerUsername) {
        for (Offer offer : product.offers) {
            if (offer.getBuyerUsername().equals(buyerUsername)) throw new NotPermittedException();
        }
    }
}
