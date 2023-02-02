package ulaval.glo2003.Utils;

public class MissingNameException extends MissingParamException {
    public MissingNameException() {
        super("Missing parameter 'name'.");
    }
}