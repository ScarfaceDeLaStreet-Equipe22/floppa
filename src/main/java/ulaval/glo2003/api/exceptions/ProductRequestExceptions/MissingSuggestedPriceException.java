package ulaval.glo2003.api.exceptions.ProductRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingSuggestedPriceException extends MissingParamException {
    public MissingSuggestedPriceException() {
        super("Missing parameter 'SuggestedPrice'.");
    }
}
