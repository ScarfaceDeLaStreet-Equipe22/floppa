package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingCategoryException extends MissingParamException {
    public MissingCategoryException() {
        super("Missing parameter 'Category'.");
    }
}
