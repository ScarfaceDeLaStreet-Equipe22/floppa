package ulaval.glo2003.domain.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import static org.junit.jupiter.api.Assertions.*;

public class ProductFiltersTests
{

    private Product testProduct;
    private String sellerId;

    private static final String VALID_PRODUCT_TITLE = "TestProduct";
    private static final String PRODUCT_DESCRIPTION = "Description";
    private static final ProductCategory VALID_PRODUCT_CATEGORY = new ProductCategory("other");
    private static final Amount PRODUCT_PRICE = new Amount("10.0");

    private static final String INVALID_SELLER_ID = "invalidSellerId";
    private static final String INVALID_PRODUCT_TITLE = "InvalidTitle";
    private static final ProductCategory INVALID_PRODUCT_CATEGORY = new ProductCategory("Sport");



    @BeforeEach
    public void setUp()
    {
        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
        testProduct = new Product(VALID_PRODUCT_TITLE, PRODUCT_DESCRIPTION, VALID_PRODUCT_CATEGORY, PRODUCT_PRICE, seller);
        sellerId = seller.getId();
    }

    @Test
    public void givenExacltyMatchingFilter_whenChecking_thenReturnsTrue()
    {
        //arrange
        ProductFilters productFilters = new ProductFilters(sellerId, VALID_PRODUCT_TITLE, VALID_PRODUCT_CATEGORY, PRODUCT_PRICE, PRODUCT_PRICE);

        //act
        boolean result = productFilters.checkProduct(testProduct);

        //assert
        assertTrue(result);
    }

    @Test
    public void givenAllNullFilter_whenChecking_thenReturnsTrue()
    {
        //arrange
        ProductFilters productFilters = new ProductFilters(null, null, null, null, null);

        //act
        boolean result = productFilters.checkProduct(testProduct);

        //assert
        assertTrue(result);
    }

    @Test
    public void givenFilterWithLowercaseTitle_whenChecking_thenReturnsTrue()
    {
        //arrange
        String title = VALID_PRODUCT_TITLE.toLowerCase();
        ProductFilters productFilters = new ProductFilters(sellerId, title, VALID_PRODUCT_CATEGORY, PRODUCT_PRICE, PRODUCT_PRICE);

        //act
        boolean result = productFilters.checkProduct(testProduct);

        //assert
        assertTrue(result);
    }

    @Test
    public void givenFilterWithPartTitle_whenChecking_thenReturnsTrue()
    {
        //arrange
        int titleLength = VALID_PRODUCT_TITLE.length();
        String title = VALID_PRODUCT_TITLE.substring(2, titleLength - 2);
        ProductFilters productFilters = new ProductFilters(sellerId, title, VALID_PRODUCT_CATEGORY, PRODUCT_PRICE, PRODUCT_PRICE);

        //act
        boolean result = productFilters.checkProduct(testProduct);

        //assert
        assertTrue(result);
    }

}
