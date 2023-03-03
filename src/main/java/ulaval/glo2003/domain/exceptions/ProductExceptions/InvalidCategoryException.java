package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidCategoryException extends InvalidParamException {
    public InvalidCategoryException() {
        super("Invalid parameter 'Category'.");
    }
}
