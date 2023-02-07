package ulaval.glo2003.domain.SellerClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class DateTime {

    private static SimpleDateFormat dateTimeFormat ;
    public String dateTime;

    public DateTime(String dateTime) {
        assertDateTime(dateTime);

        this.dateTime = dateTime;
    }

    public DateTime(){
        try {
            dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
            this.dateTime = dateTimeFormat.format(new Date());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void assertDateTime(String dateTime) {
        try {
            dateTimeFormat.parse(dateTime);
        } catch (ParseException exception) {
            throw new InvalidParamException("Invalid DateTime format");
        }
    }

    public String getDateTime() {
        return dateTime;
    }
}
