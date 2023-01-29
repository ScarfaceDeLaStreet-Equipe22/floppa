package ulaval.glo2003.util;

public class MissingBirthDateException extends MissingParamException{
    public MissingBirthDateException() {
        super("Missing birthdate.");
    }
}
