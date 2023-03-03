package ulaval.glo2003.api.exceptions.SellerRequestExceptions;

import ulaval.glo2003.api.exceptions.MissingParamException;

public class MissingBioException extends MissingParamException {
    public MissingBioException() {
        super("Missing parameter 'bio'.");
    }
}
