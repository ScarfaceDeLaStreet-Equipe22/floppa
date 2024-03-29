package ulaval.glo2003.domain.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;

public class ProductFiltersTests {

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
    public void setUp() {
        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
        SellerMongoModel sellerMongoModel = new SellerMongoModel(seller);

        testProduct =
                new Product(
                        VALID_PRODUCT_TITLE,
                        PRODUCT_DESCRIPTION,
                        VALID_PRODUCT_CATEGORY,
                        PRODUCT_PRICE,
                        sellerMongoModel);
        sellerId = sellerMongoModel.id;
        testProduct.seller = seller;
    }

    @Test
    public void givenExacltyMatchingFilter_whenChecking_thenReturnsTrue() {
        ProductFilters productFilters =
                new ProductFilters(
                        sellerId,
                        VALID_PRODUCT_TITLE,
                        VALID_PRODUCT_CATEGORY,
                        PRODUCT_PRICE,
                        PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertTrue(result);
    }

    @Test
    public void givenAllNullFilter_whenChecking_thenReturnsTrue() {
        ProductFilters productFilters = new ProductFilters(null, null, null, null, null);

        boolean result = productFilters.checkProduct(testProduct);

        assertTrue(result);
    }

    @Test
    public void givenFilterWithLowercaseTitle_whenChecking_thenReturnsTrue() {
        String title = VALID_PRODUCT_TITLE.toLowerCase();
        ProductFilters productFilters =
                new ProductFilters(
                        sellerId, title, VALID_PRODUCT_CATEGORY, PRODUCT_PRICE, PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertTrue(result);
    }

    @Test
    public void givenFilterWithPartTitle_whenChecking_thenReturnsTrue() {
        int titleLength = VALID_PRODUCT_TITLE.length();
        String title = VALID_PRODUCT_TITLE.substring(2, titleLength - 2);
        ProductFilters productFilters =
                new ProductFilters(
                        sellerId, title, VALID_PRODUCT_CATEGORY, PRODUCT_PRICE, PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertTrue(result);
    }

    @Test
    public void givenFilterWithInvalidTitle_whenChecking_thenReturnsFalse() {
        ProductFilters productFilters =
                new ProductFilters(
                        sellerId,
                        INVALID_PRODUCT_TITLE,
                        VALID_PRODUCT_CATEGORY,
                        PRODUCT_PRICE,
                        PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertFalse(result);
    }

    @Test
    public void givenFilterWithInvalidCategory_whenChecking_thenReturnsFalse() {
        ProductFilters productFilters =
                new ProductFilters(
                        sellerId,
                        VALID_PRODUCT_TITLE,
                        INVALID_PRODUCT_CATEGORY,
                        PRODUCT_PRICE,
                        PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertFalse(result);
    }

    @Test
    public void givenFilterWithInvalidSellerId_whenChecking_thenReturnsFalse() {
        ProductFilters productFilters =
                new ProductFilters(
                        INVALID_SELLER_ID,
                        VALID_PRODUCT_TITLE,
                        VALID_PRODUCT_CATEGORY,
                        PRODUCT_PRICE,
                        PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertFalse(result);
    }

    @Test
    public void givenFilterWithLowerMinPrice_whenChecking_thenReturnsTrue() {
        double minPrice = PRODUCT_PRICE.toDouble() - 1;
        Amount minPriceAmount = new Amount(Double.toString(minPrice));

        ProductFilters productFilters =
                new ProductFilters(
                        sellerId,
                        VALID_PRODUCT_TITLE,
                        VALID_PRODUCT_CATEGORY,
                        minPriceAmount,
                        PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertTrue(result);
    }

    @Test
    public void givenFilterWithHigherMinPrice_whenChecking_thenReturnsFalse() {
        double minPrice = PRODUCT_PRICE.toDouble() + 1;
        Amount minPriceAmount = new Amount(Double.toString(minPrice));

        ProductFilters productFilters =
                new ProductFilters(
                        sellerId,
                        VALID_PRODUCT_TITLE,
                        VALID_PRODUCT_CATEGORY,
                        minPriceAmount,
                        PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertFalse(result);
    }

    @Test
    public void givenFilterWithLowerMaxPrice_whenChecking_thenReturnsTrue() {
        double maxPrice = PRODUCT_PRICE.toDouble() - 1;
        Amount maxPriceAmount = new Amount(Double.toString(maxPrice));

        ProductFilters productFilters =
                new ProductFilters(
                        sellerId,
                        VALID_PRODUCT_TITLE,
                        VALID_PRODUCT_CATEGORY,
                        maxPriceAmount,
                        PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertTrue(result);
    }

    @Test
    public void givenFilterWithHigherMaxPrice_whenChecking_thenReturnsFalse() {
        double maxPrice = PRODUCT_PRICE.toDouble() + 1;
        Amount maxPriceAmount = new Amount(Double.toString(maxPrice));

        ProductFilters productFilters =
                new ProductFilters(
                        sellerId,
                        VALID_PRODUCT_TITLE,
                        VALID_PRODUCT_CATEGORY,
                        maxPriceAmount,
                        PRODUCT_PRICE);

        boolean result = productFilters.checkProduct(testProduct);

        assertFalse(result);
    }
}
