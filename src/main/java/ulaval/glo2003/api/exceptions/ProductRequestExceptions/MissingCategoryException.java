package ulaval.glo2003.api.exceptions.ProductRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingCategoryException extends MissingParamException {
    public MissingCategoryException() {
        super("Missing parameter 'Category'.");
    }
}
