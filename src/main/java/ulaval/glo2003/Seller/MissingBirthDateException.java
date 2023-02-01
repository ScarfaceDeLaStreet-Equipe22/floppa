package ulaval.glo2003.Seller;

import ulaval.glo2003.Utils.MissingParamException;

public class MissingBirthDateException extends MissingParamException {
    public MissingBirthDateException() {
        super("Missing parameter 'birthdate'.");
    }
}
