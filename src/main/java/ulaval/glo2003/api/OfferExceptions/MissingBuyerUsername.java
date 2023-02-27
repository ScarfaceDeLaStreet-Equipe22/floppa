package ulaval.glo2003.api.OfferExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingBuyerUsername extends MissingParamException {
    public MissingBuyerUsername() {
        super("Invalid header 'X-Buyer-Username'.");
    }
}
