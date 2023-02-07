package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidDescriptionException extends InvalidParamException {
    public InvalidDescriptionException() {
        super("Invalid parameter 'Description'.");
    }
}
