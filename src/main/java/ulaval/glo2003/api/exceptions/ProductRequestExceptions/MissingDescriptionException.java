package ulaval.glo2003.api.exceptions.ProductRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingDescriptionException extends MissingParamException {
    public MissingDescriptionException() {
        super("Missing parameter 'Description'.");
    }
}
