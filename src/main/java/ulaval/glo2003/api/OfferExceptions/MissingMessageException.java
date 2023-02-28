package ulaval.glo2003.api.OfferExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingMessageException extends MissingParamException {
    public MissingMessageException() {
        super("Missing parameter 'message'.");
    }
}
