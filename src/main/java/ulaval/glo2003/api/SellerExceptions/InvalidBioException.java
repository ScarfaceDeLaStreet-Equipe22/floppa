package ulaval.glo2003.api.SellerExceptions;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class InvalidBioException extends InvalidParamException {
    public InvalidBioException() {
        super("Invalid parameter 'Bio'.");
    }
}
