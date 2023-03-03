package ulaval.glo2003.domain.exceptions.OfferExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidbuyerUsername extends InvalidParamException {
    public InvalidbuyerUsername() {
        super("Invalid header 'X-Buyer-Username'.");
    }
}
