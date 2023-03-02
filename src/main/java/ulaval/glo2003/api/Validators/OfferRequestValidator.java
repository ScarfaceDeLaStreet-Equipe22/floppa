package ulaval.glo2003.api.Validators;

import ulaval.glo2003.api.Offer.OfferRequest;
import ulaval.glo2003.api.OfferExceptions.InvalidbuyerUsername;
import ulaval.glo2003.api.OfferExceptions.MissingAmountException;
import ulaval.glo2003.api.OfferExceptions.MissingMessageException;
import ulaval.glo2003.api.OfferExceptions.NotPermittedException;
import ulaval.glo2003.api.ProductExceptions.InvalidAmountException;
import ulaval.glo2003.api.ProductExceptions.InvalidDescriptionException;
import ulaval.glo2003.api.ProductExceptions.InvalidTitleException;
import ulaval.glo2003.domain.Offer;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductClasses.Amount;

public class OfferRequestValidator {

    private final OfferRequest offerRequest;
    private final Product product;
    private final String buyerUsername;

    public OfferRequestValidator(OfferRequest offerRequest, String buyerUsername, Product product){
        this.offerRequest = offerRequest;
        this.buyerUsername = buyerUsername;
        this.product = product;
    }
    public void validateRequest() {
        assertParamNotNull();
        assertParamNotEmpty();
        validateMinimumAmount();
        verifyIfBuyerAlreadyMadeAnOffer();
    }

    private void assertParamNotNull( ) {
        if (offerRequest.getAmount() == null) {
            throw new MissingAmountException();
        }
        if (offerRequest.getMessage() == null) {
            throw new MissingMessageException();
        }
        if (buyerUsername == null)
        {
            throw new InvalidbuyerUsername();
        }
    }

    private void assertParamNotEmpty() {
        if (offerRequest.getAmount().isEmpty()) {
            throw new InvalidTitleException();
        }
        if (offerRequest.getMessage().length() < 100) {
            throw new InvalidDescriptionException();
        }
    }

    private void validateMinimumAmount() {
        Double suggestedPrice = new Amount(product.getSuggestedPrice()).getAmount();
        Double offerAmount = new Amount(offerRequest.getAmount()).getAmount();

        if (offerAmount < suggestedPrice) {
            throw new InvalidAmountException();
        }
    }

    private void verifyIfBuyerAlreadyMadeAnOffer() {
        for (Offer offer : product.offerRepository.findAll()) {
            if (offer.getBuyerUsername().equals(buyerUsername))
            {
                throw new NotPermittedException();
            }
        }
    }
}
