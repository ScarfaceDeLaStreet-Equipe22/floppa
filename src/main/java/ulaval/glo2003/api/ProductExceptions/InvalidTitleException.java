package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidTitleException extends InvalidParamException {
    public InvalidTitleException() {
        super("Invalid parameter 'Title'.");
    }
}
