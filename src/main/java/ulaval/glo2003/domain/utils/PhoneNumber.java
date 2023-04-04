package ulaval.glo2003.domain.utils;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidPhoneNumberException;
@Entity
public class PhoneNumber {

    @Id
    public String numero;

    public PhoneNumber(){}
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
