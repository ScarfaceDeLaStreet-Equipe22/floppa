package ulaval.glo2003.Domain;

import ulaval.glo2003.api.Utils.InvalidParamException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    public String email;
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9_.+-]+)*@([a-zA-Z0-9_.+-]+)*(\\.[a-z]+)$";
    private static Pattern pattern;
    private Matcher matcher;

    public Email(String email) {
        EmailValidator(email);
    }

    public void EmailValidator (String email){
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

        matcher = pattern.matcher(email);
        if (matcher.matches()) {
            if (matcher.group(1)!=null && matcher.group(2)!=null && matcher.group(3).length()!=0){
                this.email = email;
            }else {
                throw new InvalidParamException("invalid email");
            }
        } else {
            throw new InvalidParamException("invalid email");
        }
    }


}
