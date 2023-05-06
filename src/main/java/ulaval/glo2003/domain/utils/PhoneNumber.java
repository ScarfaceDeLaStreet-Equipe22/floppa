package ulaval.glo2003.domain.utils;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidPhoneNumberException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
