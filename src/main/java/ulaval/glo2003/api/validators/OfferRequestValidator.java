package ulaval.glo2003.api.validators;

import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingAmountException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingMessageException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingParamBuyerUsername;
import ulaval.glo2003.api.requests.OfferRequest;

public class OfferRequestValidator implements IValidatorRequest {

    private final OfferRequest offerRequest;
    private final String buyerUsername;

    public OfferRequestValidator(OfferRequest offerRequest, String buyerUsername) {
        this.offerRequest = offerRequest;
        this.buyerUsername = buyerUsername;
    }

    @Override
    public void validateRequest() {
        assertParamNotNull();
    }

    private void assertParamNotNull() {
        if (offerRequest.getAmount() == null) {
            throw new MissingAmountException();
        }
        if (offerRequest.getMessage() == null) {
            throw new MissingMessageException();
        }
        if (buyerUsername == null) {
            throw new MissingParamBuyerUsername();
        }
    }
}
