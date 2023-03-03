package ulaval.glo2003.domain.exceptions.SellerExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidBirthdateException extends InvalidParamException {
    public InvalidBirthdateException() {
        super("Invalid parameter 'Birthdate'.");
    }
}
