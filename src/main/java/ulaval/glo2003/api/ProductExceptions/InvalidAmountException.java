package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidAmountException extends InvalidParamException {
    public InvalidAmountException() {
        super("Invalid parameter 'Amount'.");
    }
}
