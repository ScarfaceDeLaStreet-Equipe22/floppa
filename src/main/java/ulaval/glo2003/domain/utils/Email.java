package ulaval.glo2003.domain.utils;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

import java.util.Objects;
import java.util.UUID;
@Entity
public class Email {

    @Id
    public String id;
    private String email;
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//    private static Pattern pattern;
//    private Matcher matcher;

    public Email(){};
    public Email(String email) {
        assertEmail(email);
        this.id = UUID.randomUUID().toString();

    }

    public void assertEmail(String email) {

        if (email.matches(EMAIL_REGEX)){
            this.email = email;
            return;
        } else {
            throw new InvalidParamException("Invalid parameter 'Email'.");
        }

    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
