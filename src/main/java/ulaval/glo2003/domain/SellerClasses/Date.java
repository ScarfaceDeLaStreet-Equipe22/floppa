package ulaval.glo2003.domain.SellerClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import ulaval.glo2003.api.SellerExceptions.InvalidBirthdateException;
import ulaval.glo2003.api.Utils.InvalidParamException;

public class Date {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final String date;

    public Date(String date) {
        assertDate(date);

        this.date = date;
    }

    private void assertDate(String date) {
        try {
            dateFormat.parse(date);
        } catch (ParseException exception) {
            throw new InvalidBirthdateException();
        }
    }

    public String getDate() {
        return date;
    }
}
