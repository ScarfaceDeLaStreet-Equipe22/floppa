package ulaval.glo2003.api.SellerExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidEmailException extends InvalidParamException {
    public InvalidEmailException() {
        super("Invalid parameter 'Email'.");
    }
}
