package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidFilterMaxPriceException extends InvalidParamException {
    public InvalidFilterMaxPriceException() {
        super("Invalid parameter 'maxPrice'.");
    }
}
