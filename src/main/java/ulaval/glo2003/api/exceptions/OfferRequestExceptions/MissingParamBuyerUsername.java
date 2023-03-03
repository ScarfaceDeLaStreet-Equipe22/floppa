package ulaval.glo2003.api.exceptions.OfferRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingParamBuyerUsername extends MissingParamException {
    public MissingParamBuyerUsername() {
        super("Missing header 'X-Buyer-Username'.");
    }
}
