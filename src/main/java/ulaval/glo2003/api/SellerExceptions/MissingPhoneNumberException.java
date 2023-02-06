package ulaval.glo2003.api.SellerExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingPhoneNumberException extends MissingParamException {
    public MissingPhoneNumberException() { super("\"Missing parameter 'PhoneNumber'."); }
}
