package ulaval.glo2003.api.SellerExceptions;

import ulaval.glo2003.api.Utils.MissingParamException;

public class MissingBioException extends MissingParamException {
    public MissingBioException() {
        super("Missing parameter 'bio'.");
    }
}