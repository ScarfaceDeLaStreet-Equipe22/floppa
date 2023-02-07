package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingTitleException extends MissingParamException {
    public MissingTitleException() {
        super("Missing parameter 'Title'.");
    }
}
