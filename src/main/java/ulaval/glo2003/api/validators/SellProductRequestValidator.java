package ulaval.glo2003.api.validators;

import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingParamBuyerUsername;
import ulaval.glo2003.api.requests.SellProductRequest;

public class SellProductRequestValidator implements IValidatorRequest {

    private final SellProductRequest sellProductRequest;
    private final String username;

    public SellProductRequestValidator(SellProductRequest sellProductRequest, SellProductRequest sellProductRequest1, String username) {
        this.sellProductRequest = sellProductRequest;
        this.username = username;
    }

    @Override
    public void validateRequest() {
        assertParamNotNull();
    }

    private void assertParamNotNull() {
        if (username == null) {
            throw new MissingParamBuyerUsername();
        }
    }
}
