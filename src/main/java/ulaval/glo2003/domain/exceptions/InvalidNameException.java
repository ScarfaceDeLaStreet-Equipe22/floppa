package ulaval.glo2003.domain.exceptions;

public class InvalidNameException extends InvalidParamException {
    public InvalidNameException() {
        super("Invalid parameter 'Name'.");
    }
}
