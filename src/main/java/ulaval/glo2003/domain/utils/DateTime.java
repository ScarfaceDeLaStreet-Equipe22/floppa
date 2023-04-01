package ulaval.glo2003.domain.utils;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class DateTime {

    @Id
    public String dateTime;

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime() {
        try {
            DateTimeFormatter dateTimeFormat =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
            this.dateTime = LocalDateTime.now().format(dateTimeFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDateTime() {
        return dateTime;
    }
}
