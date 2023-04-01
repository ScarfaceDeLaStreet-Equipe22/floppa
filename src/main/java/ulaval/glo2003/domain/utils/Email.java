package ulaval.glo2003.domain.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
@Entity
public class Email {

    @Id
    public String id;
    private String email;
    private static final String EMAIL_REGEX =
            "^([a-zA-Z0-9_.+-]+)*@([a-zA-Z0-9_.+-]+)*(\\.[a-z]+)$";
    private static Pattern pattern;
    private Matcher matcher;

    public Email(String email) {
        assertEmail(email);
        this.id = UUID.randomUUID().toString();

    }

    public void assertEmail(String email) {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

        matcher = pattern.matcher(email);
        if (matcher.matches()) {
            if (matcher.group(1) != null
                    && matcher.group(2) != null
                    && matcher.group(3).length() != 0) {
                this.email = email;
            } else {
                throw new InvalidParamException("Invalid parameter 'Email'.");
            }
        } else {
            throw new InvalidParamException("Invalid parameter 'Email'.");
        }
    }

    public String getEmail() {
        return email;
    }
}
