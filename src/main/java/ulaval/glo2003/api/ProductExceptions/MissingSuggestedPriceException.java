package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingSuggestedPriceException extends MissingParamException {
    public MissingSuggestedPriceException() {
        super("Missing parameter 'SuggestedPrice'.");
    }
}
