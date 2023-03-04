package ulaval.glo2003.domain.validators;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidCategoryException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidDescriptionException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidSuggestedPriceException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidTitleException;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

import static org.junit.jupiter.api.Assertions.*;

public class ProductValidatorTest {
    private static final String VALID_TITLE = "Valid Title";
    private static final String VALID_DESCRIPTION = "Valid Description";
    private static final ProductCategory VALID_CATEGORY = new ProductCategory("Sport");
    private static final Amount VALID_SUGGESTED_PRICE = new Amount("10.50");

    private static final Seller SELLER = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");

    @Test
    public void givenProductWithValidProperties_whenValidatingEntity_thenDoesNotThrowException() {
        // arrange
        Product product = new Product(VALID_TITLE, VALID_DESCRIPTION, VALID_CATEGORY, VALID_SUGGESTED_PRICE, SELLER);
        ProductValidator validator = new ProductValidator(product);

        // act
        Executable executable = validator::validateEntity;

        // assert
        assertDoesNotThrow(executable);
    }

    @Test
    public void givenProductWithEmptyTitle_whenValidatingEntity_thenThrowsInvalidTitleException() {
        // arrange
        Product product = new Product("", VALID_DESCRIPTION, VALID_CATEGORY, VALID_SUGGESTED_PRICE, SELLER);
        ProductValidator validator = new ProductValidator(product);

        // act
        Executable executable = validator::validateEntity;

        // assert
        assertThrows(InvalidTitleException.class, executable);
    }

    @Test
    public void givenProductWithEmptyDescription_whenValidatingEntity_thenThrowsInvalidDescriptionException() {
        // arrange
        Product product = new Product(VALID_TITLE, "", VALID_CATEGORY, VALID_SUGGESTED_PRICE, SELLER);
        ProductValidator validator = new ProductValidator(product);

        // act
        Executable executable = validator::validateEntity;

        // assert
        assertThrows(InvalidDescriptionException.class, executable);
    }

    @Test
    public void givenProductWithInvalidSuggestedPrice_whenValidatingEntity_thenThrowsInvalidSuggestedPriceException() {
        // arrange
        Amount invalidSuggestedPrice = new Amount("0");
        Product product = new Product(VALID_TITLE, VALID_DESCRIPTION, VALID_CATEGORY, invalidSuggestedPrice, SELLER);
        ProductValidator validator = new ProductValidator(product);

        // act
        Executable executable = validator::validateEntity;

        // assert
        assertThrows(InvalidSuggestedPriceException.class, executable);
    }
}