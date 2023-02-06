package ulaval.glo2003.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date
{
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final String date;

    public Date(String date) {
        assertDate(date);

        this.date = date;
    }

    private void assertDate(String date)
    {
        try
        {
            dateFormat.parse(date);
        }catch (ParseException exception)
        {
            throw new RuntimeException("Invalid Date format");
        }
    }

    public String getDate() {
        return date;
    }
}