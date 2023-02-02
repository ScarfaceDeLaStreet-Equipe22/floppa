package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Utils.InvalidParamException;

public class InvalidBioException extends InvalidParamException {
    public InvalidBioException() {
        super("Invalid parameter 'Bio'.");
    }
}
