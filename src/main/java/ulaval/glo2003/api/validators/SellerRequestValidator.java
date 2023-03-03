package ulaval.glo2003.api.validators;

import ulaval.glo2003.api.exceptions.SellerRequestExceptions.*;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.domain.exceptions.InvalidNameException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBioException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBirthdateException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidEmailException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidPhoneNumberException;

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
