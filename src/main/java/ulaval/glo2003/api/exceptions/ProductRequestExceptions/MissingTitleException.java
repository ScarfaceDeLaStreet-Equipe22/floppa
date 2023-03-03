package ulaval.glo2003.api.exceptions.ProductRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingTitleException extends MissingParamException {
    public MissingTitleException() {
        super("Missing parameter 'Title'.");
    }
}
