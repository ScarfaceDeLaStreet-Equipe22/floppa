package ulaval.glo2003.api.mappers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.exceptions.InvalidParamException;

public class ProductFiltersMapperTests {

    private ProductFiltersMapper productFiltersMapper;
    private static final String VALID_SELLER_ID = "testSellerId";
    private static final String VALID_TITLE = "title";
    private static final String VALID_CATEGORY_NAME = "other";
    private static final String VALID_PRICE = "10.0";

    @BeforeEach
    public void setUp() {
        productFiltersMapper = new ProductFiltersMapper();
    }

    @Test
    public void givenAllValidFilters_whenMapping_thenDoesNotThrow() {
        Executable executable =
                () ->
                        productFiltersMapper.mapQueryParamsToRequest(
                                VALID_SELLER_ID,
                                VALID_TITLE,
                                VALID_CATEGORY_NAME,
                                VALID_PRICE,
                                VALID_PRICE);

        assertDoesNotThrow(executable);
    }

    @Test
    public void givenAllNullFilters_whenMapping_thenDoesNotThrow() {
        Executable executable =
                () -> productFiltersMapper.mapQueryParamsToRequest(null, null, null, null, null);

        assertDoesNotThrow(executable);
    }

    @Test
    public void givenInvalidCategory_whenMapping_thenThrowInvalidParamException() {
        String invalidCategoryName = "invalid-category";

        Executable executable =
                () ->
                        productFiltersMapper.mapQueryParamsToRequest(
                                VALID_SELLER_ID,
                                VALID_TITLE,
                                invalidCategoryName,
                                VALID_PRICE,
                                VALID_PRICE);

        assertThrows(InvalidParamException.class, executable);
    }

    @Test
    public void givenInvalidMinPrice_whenMapping_thenThrowInvalidParamException() {
        String invalidPrice = "aaaa";

        Executable executable =
                () ->
                        productFiltersMapper.mapQueryParamsToRequest(
                                VALID_SELLER_ID,
                                VALID_TITLE,
                                VALID_CATEGORY_NAME,
                                invalidPrice,
                                VALID_PRICE);

        assertThrows(InvalidParamException.class, executable);
    }

    @Test
    public void givenInvalidMaxPrice_whenMapping_thenThrowInvalidParamException() {
        String invalidPrice = "aaaa";

        Executable executable =
                () ->
                        productFiltersMapper.mapQueryParamsToRequest(
                                VALID_SELLER_ID,
                                VALID_TITLE,
                                VALID_CATEGORY_NAME,
                                VALID_PRICE,
                                invalidPrice);

        assertThrows(InvalidParamException.class, executable);
    }
}
