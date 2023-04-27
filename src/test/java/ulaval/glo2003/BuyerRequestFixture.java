package ulaval.glo2003;

import ulaval.glo2003.api.requests.BuyerRequest;
import ulaval.glo2003.api.requests.SellerRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyerRequestFixture
{

    private static String name = "Test Buyer Name";
    private static String birthdate = LocalDate.now().minus(18, ChronoUnit.YEARS).format(DateTimeFormatter.ISO_DATE);
    private static String email = "buyer@test.com";
    private static String phoneNumber = "11230001234";
    private static List<String> preferences = List.of("beauty", "electronics");

    public static BuyerRequest valid()
    {
        return new BuyerRequest(name, birthdate, email, phoneNumber, preferences);
    }

    public static BuyerRequest withoutPreferences()
    {
        return new BuyerRequest(name, birthdate, email, phoneNumber, null);
    }

    public static BuyerRequest invalidPreferences()
    {
        return new BuyerRequest(name, birthdate, email, phoneNumber, List.of("invalid-category"));
    }

    public static BuyerRequest missingField()
    {
        return new BuyerRequest(null, birthdate, email, phoneNumber, preferences);
    }

    public static BuyerRequest emptyField()
    {
        return new BuyerRequest("", birthdate, email, phoneNumber, preferences);
    }

    public static BuyerRequest tooYoung()
    {
        String tooYoungBirthdate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        return new BuyerRequest(name, tooYoungBirthdate, email, phoneNumber, preferences);
    }
}
