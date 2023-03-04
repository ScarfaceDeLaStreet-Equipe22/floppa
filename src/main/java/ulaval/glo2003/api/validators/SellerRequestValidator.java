package ulaval.glo2003.api.validators;

import ulaval.glo2003.api.exceptions.SellerRequestExceptions.*;
import ulaval.glo2003.api.requests.SellerRequest;


public class SellerRequestValidator implements IValidatorRequest {

    private final SellerRequest sellerRequest;

    public SellerRequestValidator(SellerRequest sellerRequest) {
        this.sellerRequest = sellerRequest;
    }

    @Override
    public void validateRequest() {
        assertParamNotNull();
    }

    private void assertParamNotNull() {
        if (sellerRequest.getName() == null) {
            throw new MissingNameException();
        }
        if (sellerRequest.getBio() == null) {
            throw new MissingBioException();
        }
        if (sellerRequest.getBirthdate() == null) {
            throw new MissingBirthDateException();
        }
        if (sellerRequest.getEmail() == null) {
            throw new MissingEmailException();
        }
        if (sellerRequest.getPhoneNumber() == null) {
            throw new MissingPhoneNumberException();
        }
    }
}
