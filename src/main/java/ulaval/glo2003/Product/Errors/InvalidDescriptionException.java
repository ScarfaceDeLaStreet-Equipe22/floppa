package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Utils.InvalidParamException;

public class InvalidDescriptionException extends InvalidParamException {
    public InvalidDescriptionException() {
        super("Invalid parameter 'Description'.");
    }
}
