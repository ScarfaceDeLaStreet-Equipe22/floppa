package ulaval.glo2003.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBirthdateException;

@Entity
public class Date {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public  String date;
    @Id
    public  String id;

    public Date(){};
    public Date(String date) {
        assertDate(date);
        this.date = date;
        this.id = UUID.randomUUID().toString();
    }

    private void assertDate(String date) {
        try {
            dateFormat.parse(date);
        } catch (ParseException exception) {
            throw new InvalidBirthdateException();
        }
    }

    public int getYearsBetweenNow() {
        LocalDate birthdate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }

    public String getDate() {
        return date;
    }

    public static String getFormattedCurrentDate() {
        java.util.Date currentDate = new java.util.Date();
        return dateFormat.format(currentDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return Objects.equals(date, date1.date) && Objects.equals(id, date1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, id);
    }
}
