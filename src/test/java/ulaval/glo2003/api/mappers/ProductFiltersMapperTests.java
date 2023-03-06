package ulaval.glo2003.api.mappers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.exceptions.InvalidParamException;
import ulaval.glo2003.domain.utils.ProductFilters;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFiltersMapperTests
{
    private ProductFiltersMapper productFiltersMapper;

    private static final String VALID_SELLER_ID = "testSellerId";
    private static final String VALID_TITLE = "title";
    private static final String VALID_CATEGORY_NAME = "other";
    private static final String VALID_PRICE = "10.0";

    @BeforeEach
    public void setUp()
    {
        productFiltersMapper = new ProductFiltersMapper();
    }

    @Test
    public void givenAllValidFilters_whenMapping_thenDoesNotThrow()
    {
        //act
        Executable executable = () -> productFiltersMapper.mapQueryParamsToRequest(VALID_SELLER_ID, VALID_TITLE, VALID_CATEGORY_NAME, VALID_PRICE, VALID_PRICE);

        //assert
        assertDoesNotThrow(executable);
    }


}
