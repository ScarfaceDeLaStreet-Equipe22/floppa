package ulaval.glo2003.api.Validators;

public interface IValidator <R>{
    void validateRequest();
    void assertParamNotNull(R request);
    void assertParamNotEmpty(R request);
}
