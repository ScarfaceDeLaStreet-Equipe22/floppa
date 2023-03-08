package ulaval.glo2003.domain.exceptions.OfferExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidAmountException extends InvalidParamException {
    public InvalidAmountException() {
        super("Invalid parameter 'Amount'.");
    }
}
