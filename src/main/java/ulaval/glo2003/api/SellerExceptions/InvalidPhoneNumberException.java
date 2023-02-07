package ulaval.glo2003.api.SellerExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidPhoneNumberException extends InvalidParamException {
    public InvalidPhoneNumberException() {
        super("Invalid parameter 'PhoneNumber'.");
    }
}
