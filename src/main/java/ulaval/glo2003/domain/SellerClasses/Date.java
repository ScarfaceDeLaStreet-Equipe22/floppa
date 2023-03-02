package ulaval.glo2003.domain.SellerClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;

import ulaval.glo2003.api.SellerExceptions.InvalidBirthdateException;

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

    public void assertAge() {

        try {
            java.util.Date birthdate = dateFormat.parse(date);

            LocalDate birthdateLocal = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = Period.between(birthdateLocal, LocalDate.now()).getYears();

            if (age < 18) {
                throw new InvalidBirthdateException();
            }
        } catch (ParseException exception) {
            throw new InvalidBirthdateException();
        }

    }

    public String getDate() {
        return date;
    }
}
