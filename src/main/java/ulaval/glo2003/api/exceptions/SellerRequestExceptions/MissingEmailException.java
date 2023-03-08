package ulaval.glo2003.api.exceptions.SellerRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingEmailException extends MissingParamException {
    public MissingEmailException() {
        super("Missing parameter 'Email'.");
    }
}
