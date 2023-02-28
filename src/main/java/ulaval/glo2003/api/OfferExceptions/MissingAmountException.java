package ulaval.glo2003.api.OfferExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingAmountException extends MissingParamException {
    public MissingAmountException() {
        super("Missing parameter 'amount'.");
    }
}
