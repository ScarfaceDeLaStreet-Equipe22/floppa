package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Utils.MissingParamException;

public class MissingTitleException extends MissingParamException {
    public MissingTitleException() {
        super("Missing parameter 'Title'.");
    }
}
