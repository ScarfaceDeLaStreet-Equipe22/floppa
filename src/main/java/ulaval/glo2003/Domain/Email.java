package ulaval.glo2003.Domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    public String email;
    //private static final String EMAIL_REGEX = "^([\\w-\\+]+)@([\\w-]+)(\\.[a-z]{2,})$";
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_.+-]+)*@([a-zA-Z0-9_.+-]+)*(\\.[a-z]+)$";
    private static Pattern pattern;
    private Matcher matcher;

    public Email(String email) {

        this.email = email;
        EmailValidator(this.email);
    }

    public void EmailValidator (String email){
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

        matcher = pattern.matcher(email);
        if (matcher.matches()) {
            System.out.println("Id: " + matcher.group(1));
            System.out.println("Service: " + matcher.group(2));
            System.out.println("Extension: " + matcher.group(3));
        } else {
            System.out.println("Email address is not valid");
        }
    }

    public String getEmail() {
        return email;
    }

}
