package ulaval.glo2003.domain.exceptions.SellerExceptions;

import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class InvalidBioException extends InvalidParamException {
    public InvalidBioException() {
        super("Invalid parameter 'Bio'.");
    }
}
