package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Utils.MissingParamException;

public class MissingSuggestedPriceException extends MissingParamException {
    public MissingSuggestedPriceException() {
        super("Missing parameter 'SuggestedPrice'.");
    }
}
