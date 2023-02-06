package ulaval.glo2003.api.SellerExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingEmailException extends MissingParamException {
    public MissingEmailException() { super("Missing parameter 'Email'."); }
}
