package ulaval.glo2003.api.Utils;

public class InvalidNameException extends InvalidParamException {
    public InvalidNameException() {
        super("Invalid parameter 'Name'.");
    }
}
