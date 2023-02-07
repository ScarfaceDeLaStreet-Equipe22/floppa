package ulaval.glo2003.api.Utils;

public class MissingNameException extends MissingParamException {
    public MissingNameException() {
        super("Missing parameter 'name'.");
    }
}
