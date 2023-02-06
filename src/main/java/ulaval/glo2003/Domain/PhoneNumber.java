package ulaval.glo2003.Domain;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class PhoneNumber {
    private String numero ;
    public PhoneNumber(String numero) {
        phoneNumberValidator(numero);
    }

    public String getNumero() {
        return numero;
    }

    private void phoneNumberValidator(String numero){
        if (numero.matches("\\d+") && numero.length() == 11) {
            this.numero = numero;
        } else {
            throw new InvalidParamException("invalid phone number");
        }
    }
}
