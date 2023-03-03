package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidTitleException extends InvalidParamException {
    public InvalidTitleException() {
        super("Invalid parameter 'Title'.");
    }
}
