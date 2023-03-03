package ulaval.glo2003.domain.utils;

import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidPhoneNumberException;

public class PhoneNumber {
    private String numero;

    public PhoneNumber(String numero) {
        assertPhoneNumber(numero);
    }

    public String getNumero() {
        return numero;
    }

    private void assertPhoneNumber(String numero) {
        if (numero.matches("\\d+") && numero.length() == 11) {
            this.numero = numero;
        } else {
            throw new InvalidPhoneNumberException();
        }
    }
}
