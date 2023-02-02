package ulaval.glo2003.Seller.Exceptions;

import ulaval.glo2003.Utils.MissingParamException;

public class MissingBioException extends MissingParamException {
    public MissingBioException() {
        super("Missing parameter 'bio'.");
    }
}