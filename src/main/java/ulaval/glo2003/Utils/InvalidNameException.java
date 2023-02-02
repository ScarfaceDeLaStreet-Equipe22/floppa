package ulaval.glo2003.Utils;

import ulaval.glo2003.Utils.InvalidParamException;

public class InvalidNameException extends InvalidParamException {
    public InvalidNameException() {
        super("Invalid parameter 'Name'.");
    }
}
