package ulaval.glo2003.domain.validators;

import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidMessageException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.NotPermittedException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidTitleException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.OfferAlreadyExistsException;

public class OfferValidator implements IValidator {

    private final Offer offer;
    private final Product product;

    public OfferValidator(Offer offer, Product product) {
        this.offer = offer;
        this.product = product;
    }

    @Override
    public void validateEntity() {
        assertParamNotEmpty();
        assertMinimumAmount();
        assertOfferAlreadyMade();
    }

    private void assertParamNotEmpty() {
        if (String.valueOf(offer.getAmount()).isEmpty()) {
            throw new InvalidTitleException();
        }
        if (offer.getMessage().length() < 100) {
            throw new InvalidMessageException();
        }
    }

    private void assertMinimumAmount() {
        double suggestedPrice = product.getSuggestedPrice().toDouble();
        double offerAmount = offer.getAmount();

        if (offerAmount < suggestedPrice) {
            throw new InvalidAmountException();
        }
    }

    private void assertOfferAlreadyMade() {
        for (Offer currentOffer : product.offers) {
            if (currentOffer.getBuyerUsername().equals(offer.getBuyerUsername())) {
                throw new OfferAlreadyExistsException();
            }
        }
    }
}
