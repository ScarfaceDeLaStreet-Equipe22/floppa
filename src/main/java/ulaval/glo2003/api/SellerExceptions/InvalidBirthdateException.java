package ulaval.glo2003.api.SellerExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidBirthdateException extends InvalidParamException {
    public InvalidBirthdateException() {
        super("Invalid parameter 'Birthdate'.");
    }
}
