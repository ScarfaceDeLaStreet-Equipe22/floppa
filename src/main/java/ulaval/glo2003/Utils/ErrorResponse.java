package ulaval.glo2003.Utils;

public class ErrorResponse {
    public ExceptionCode code;
    public String description;

    public ErrorResponse(ExceptionCode code, String description) {
        this.code = code;
        this.description = description;
    }
}
