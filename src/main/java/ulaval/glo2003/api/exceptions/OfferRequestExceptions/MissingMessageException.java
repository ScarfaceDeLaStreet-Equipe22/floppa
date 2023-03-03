package ulaval.glo2003.api.exceptions.OfferRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingMessageException extends MissingParamException {
    public MissingMessageException() {
        super("Missing parameter 'message'.");
    }
}
