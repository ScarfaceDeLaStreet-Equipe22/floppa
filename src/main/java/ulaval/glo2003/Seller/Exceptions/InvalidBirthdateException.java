package ulaval.glo2003.Seller.Exceptions;


import ulaval.glo2003.Utils.InvalidParamException;

public class InvalidBirthdateException extends InvalidParamException {
    public InvalidBirthdateException() {
        super("Invalid parameter 'Birthdate'.");
    }
}
