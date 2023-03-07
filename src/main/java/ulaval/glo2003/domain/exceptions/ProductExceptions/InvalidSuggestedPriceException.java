package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidSuggestedPriceException extends InvalidParamException {
    public InvalidSuggestedPriceException() {
        super("Invalid parameter 'SuggestedPrice'.");
    }
}
