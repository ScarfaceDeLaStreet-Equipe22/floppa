package ulaval.glo2003.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTime {

    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss.SSSZ");
    public final String dateTime;

    public DateTime(String dateTime) {
        assertDateTime(dateTime);

        this.dateTime = dateTime;
    }

    private void assertDateTime(String dateTime)
    {
        try
        {
            dateTimeFormat.parse(dateTime);
        }catch (ParseException exception)
        {
            throw new RuntimeException("Invalid DateTime format");
        }
    }

    public String getDateTime() {
        return dateTime;
    }

}
