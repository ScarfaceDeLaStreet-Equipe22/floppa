package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidSuggestedPriceException extends InvalidParamException {
    public InvalidSuggestedPriceException() {
        super("Invalid parameter 'SuggestedPrice'.");
    }
}
