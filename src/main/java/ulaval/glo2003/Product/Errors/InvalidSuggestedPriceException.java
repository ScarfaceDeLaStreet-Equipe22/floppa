package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Utils.InvalidParamException;

public class InvalidSuggestedPriceException extends InvalidParamException {
    public InvalidSuggestedPriceException() {
        super("Invalid parameter 'SuggestedPrice'.");
    }
}
