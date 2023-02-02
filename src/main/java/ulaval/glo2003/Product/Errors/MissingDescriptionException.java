package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Utils.MissingParamException;

public class MissingDescriptionException extends MissingParamException {
    public MissingDescriptionException() {
        super("Missing parameter 'Description'.");
    }
}
