package ulaval.glo2003.domain.utils;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateTime {
    public String dateTime;

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public DateTime() {
        try {
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
            this.dateTime =  LocalDateTime.now().format(dateTimeFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDateTime() {
        return dateTime;
    }
}
