package ulaval.glo2003.api.validators;

import ulaval.glo2003.api.exceptions.SellerRequestExceptions.MissingBirthDateException;
import ulaval.glo2003.api.exceptions.SellerRequestExceptions.MissingEmailException;
import ulaval.glo2003.api.exceptions.SellerRequestExceptions.MissingNameException;
import ulaval.glo2003.api.exceptions.SellerRequestExceptions.MissingPhoneNumberException;
import ulaval.glo2003.api.requests.BuyerRequest;

public class BuyerRequestValidator implements IValidatorRequest {

    private final BuyerRequest buyerRequest;

    public BuyerRequestValidator(BuyerRequest buyerRequest) {
        this.buyerRequest = buyerRequest;
    }

    @Override
    public void validateRequest() {
        assertParamNotNull();
    }

    private void assertParamNotNull() {
        if (buyerRequest.getName() == null) {
            throw new MissingNameException();
        }
        if (buyerRequest.getBirthdate() == null) {
            throw new MissingBirthDateException();
        }
        if (buyerRequest.getEmail() == null) {
            throw new MissingEmailException();
        }
        if (buyerRequest.getPhoneNumber() == null) {
            throw new MissingPhoneNumberException();
        }
    }
}
