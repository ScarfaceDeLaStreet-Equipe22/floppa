package ulaval.glo2003.api.exceptions.SellerRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingNameException extends MissingParamException {
    public MissingNameException() {
        super("Missing parameter 'name'.");
    }
}
