package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidCategoryException extends InvalidParamException {
    public InvalidCategoryException() {
        super("Invalid parameter 'Category'.");
    }
}
