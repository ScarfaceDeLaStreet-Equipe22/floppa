package ulaval.glo2003.domain.exceptions.SellerExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidEmailException extends InvalidParamException {
    public InvalidEmailException() {
        super("Invalid parameter 'Email'.");
    }
}
