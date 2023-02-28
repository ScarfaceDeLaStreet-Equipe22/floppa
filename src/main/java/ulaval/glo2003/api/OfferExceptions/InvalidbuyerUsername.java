package ulaval.glo2003.api.OfferExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidbuyerUsername extends InvalidParamException {
    public InvalidbuyerUsername() {
        super("Invalid header 'X-Buyer-Username'.");
    }
}
