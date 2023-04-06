package ulaval.glo2003;

import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.domain.entities.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ProductRequestFixture
{

    private static String title = "Product Title";
    private static String description = "Product description";
    private static String suggestedPrice = "1.0";
    private static String category = "other";
    public static ProductRequest valid()
    {
        return new ProductRequest(title, description, suggestedPrice, category);
    }

    public static ProductRequest missingField()
    {
        return new ProductRequest(null, description, suggestedPrice, category);
    }

    public static ProductRequest emptyField()
    {
        return new ProductRequest("", description, suggestedPrice, category);
    }

    public static ProductRequest invalidPrice()
    {
        String invalidPrice = "AAAAA";
        return new ProductRequest(title, description, invalidPrice, category);    }
}
