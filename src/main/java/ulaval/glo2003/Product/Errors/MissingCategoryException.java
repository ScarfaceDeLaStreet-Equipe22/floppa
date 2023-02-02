package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Utils.MissingParamException;

public class MissingCategoryException extends MissingParamException {
    public MissingCategoryException() {
        super("Missing parameter 'Category'.");
    }
}
