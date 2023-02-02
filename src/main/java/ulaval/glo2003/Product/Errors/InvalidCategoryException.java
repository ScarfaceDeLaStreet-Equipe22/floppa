package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Utils.InvalidParamException;

public class InvalidCategoryException extends InvalidParamException {
    public InvalidCategoryException() {
        super("Invalid parameter 'Category'.");
    }
}
