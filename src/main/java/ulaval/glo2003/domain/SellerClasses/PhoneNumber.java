package ulaval.glo2003.domain.SellerClasses;

import ulaval.glo2003.api.SellerExceptions.InvalidPhoneNumberException;

public class PhoneNumber {
    private String numero;

    public PhoneNumber(String numero) {
        phoneNumberValidator(numero);
    }

    public String getNumero() {
        return numero;
    }

    private void phoneNumberValidator(String numero) {
        if (numero.matches("\\d+") && numero.length() == 11) {
            this.numero = numero;
        } else {
            throw new InvalidPhoneNumberException();
        }
    }
}
