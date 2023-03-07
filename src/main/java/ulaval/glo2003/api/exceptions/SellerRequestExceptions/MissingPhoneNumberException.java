package ulaval.glo2003.api.exceptions.SellerRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingPhoneNumberException extends MissingParamException {
    public MissingPhoneNumberException() {
        super("Missing parameter 'PhoneNumber'.");
    }
}
