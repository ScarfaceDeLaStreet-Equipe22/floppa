package ulaval.glo2003.api.exceptions.SellerRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingBirthDateException extends MissingParamException {
    public MissingBirthDateException() {
        super("Missing parameter 'birthdate'.");
    }
}
