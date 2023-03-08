package ulaval.glo2003.api.exceptions.ProductRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingSellerIdException extends MissingParamException {
    public MissingSellerIdException() {
        super("Id of the seller is not valid.");
    }
}
