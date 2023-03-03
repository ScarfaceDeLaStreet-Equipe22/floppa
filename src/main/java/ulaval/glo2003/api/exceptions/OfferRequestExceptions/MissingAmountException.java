package ulaval.glo2003.api.exceptions.OfferRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingAmountException extends MissingParamException {
    public MissingAmountException() {
        super("Missing parameter 'amount'.");
    }
}
