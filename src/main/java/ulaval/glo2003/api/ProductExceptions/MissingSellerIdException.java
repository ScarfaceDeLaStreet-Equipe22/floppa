package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingSellerIdException extends MissingParamException {
    public MissingSellerIdException() {
        super("Id of the seller is not valid.");
    }
}
