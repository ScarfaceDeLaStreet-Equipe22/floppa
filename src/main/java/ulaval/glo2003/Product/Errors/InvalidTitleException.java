package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Utils.InvalidParamException;

public class InvalidTitleException extends InvalidParamException {
    public InvalidTitleException() {
        super("Invalid parameter 'Title'.");
    }
}
