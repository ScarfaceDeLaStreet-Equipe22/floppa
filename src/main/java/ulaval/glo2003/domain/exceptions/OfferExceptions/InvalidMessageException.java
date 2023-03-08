package ulaval.glo2003.domain.exceptions.OfferExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidMessageException extends InvalidParamException {
    public InvalidMessageException() {
        super("Invalid parameter 'message'.");
    }
}
