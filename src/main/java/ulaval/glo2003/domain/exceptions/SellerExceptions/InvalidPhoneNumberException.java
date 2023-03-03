package ulaval.glo2003.domain.exceptions.SellerExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidPhoneNumberException extends InvalidParamException {
    public InvalidPhoneNumberException() {
        super("Invalid parameter 'PhoneNumber'.");
    }
}
