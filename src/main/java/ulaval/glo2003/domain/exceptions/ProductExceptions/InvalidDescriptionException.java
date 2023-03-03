package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidDescriptionException extends InvalidParamException {
    public InvalidDescriptionException() {
        super("Invalid parameter 'Description'.");
    }
}
