package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidFilterMinPriceException extends InvalidParamException {
    public InvalidFilterMinPriceException() {
        super("Invalid parameter 'minPrice'.");
    }
}
