package ulaval.glo2003;

import ulaval.glo2003.api.requests.SellerRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SellerRequestFixture
{

    private static String name;
    private static String bio;
    private static String birthdate = LocalDate.now().minus(18, ChronoUnit.YEARS).format(DateTimeFormatter.ISO_DATE);
    private static String email = "seller@test.com";
    private static String phoneNumber = "11230001234";

    public static SellerRequest valid()
    {
        return new SellerRequest(name, bio, birthdate, email, phoneNumber);
    }

    public static SellerRequest missingField()
    {
        return new SellerRequest(null, bio, birthdate, email, phoneNumber);
    }

    public static SellerRequest emptyField()
    {
        return new SellerRequest("", bio, birthdate, email, phoneNumber);
    }

    public static SellerRequest tooYoung()
    {
        String tooYoungBirthdate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        return new SellerRequest(name, bio, tooYoungBirthdate, email, phoneNumber);
    }
}
