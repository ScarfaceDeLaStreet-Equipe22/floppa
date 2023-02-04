package ulaval.glo2003.api.SellerExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingBirthDateException extends MissingParamException {
    public MissingBirthDateException() {
        super("Missing parameter 'birthdate'.");
    }
}
