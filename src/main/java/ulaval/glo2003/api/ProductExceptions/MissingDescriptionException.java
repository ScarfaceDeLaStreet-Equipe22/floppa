package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingDescriptionException extends MissingParamException {
    public MissingDescriptionException() {
        super("Missing parameter 'Description'.");
    }
}
