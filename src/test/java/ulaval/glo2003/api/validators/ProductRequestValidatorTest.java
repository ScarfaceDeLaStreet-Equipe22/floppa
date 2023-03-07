package ulaval.glo2003.api.validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.api.exceptions.ProductRequestExceptions.MissingCategoryException;
import ulaval.glo2003.api.exceptions.ProductRequestExceptions.MissingDescriptionException;
import ulaval.glo2003.api.exceptions.ProductRequestExceptions.MissingSuggestedPriceException;
import ulaval.glo2003.api.exceptions.ProductRequestExceptions.MissingTitleException;
import ulaval.glo2003.api.requests.ProductRequest;

class ProductRequestValidatorTest {
    private ProductRequestValidator productRequestValidator;

    @Test
    public void givenNullTitle_whenValidatingRequest_thenThrowsMissingTitleException() {
        ProductRequest productRequest =
                new ProductRequest(null, "description", "category", "10.00");
        productRequestValidator = new ProductRequestValidator(productRequest);

        // act
        Executable executable = productRequestValidator::validateRequest;

        // assert
        assertThrows(MissingTitleException.class, executable);
    }

    @Test
    public void givenNullDescription_whenValidatingRequest_thenThrowsMissingDescriptionException() {
        // arrange
        ProductRequest productRequest = new ProductRequest("title", null, "category", "10.00");
        productRequestValidator = new ProductRequestValidator(productRequest);

        // act
        Executable executable = productRequestValidator::validateRequest;

        assertThrows(MissingDescriptionException.class, executable);
    }

    @Test
    public void givenNullCategory_whenValidatingRequest_thenThrowsMissingCategoryException() {
        // arrange
        ProductRequest productRequest = new ProductRequest("title", "description", null, "10.00");
        productRequestValidator = new ProductRequestValidator(productRequest);

        // act
        Executable executable = productRequestValidator::validateRequest;

        // assert
        assertThrows(MissingCategoryException.class, executable);
    }

    @Test
    public void
            givenNullSuggestedPrice_whenValidatingRequest_thenThrowsMissingSuggestedPriceException() {
        // arrange
        ProductRequest productRequest =
                new ProductRequest("title", "description", "category", null);
        productRequestValidator = new ProductRequestValidator(productRequest);

        // act
        Executable executable = productRequestValidator::validateRequest;

        // assert
        assertThrows(MissingSuggestedPriceException.class, executable);
    }

    @Test
    public void givenValidRequest_whenValidatingRequest_thenDoesNotThrowException() {
        // arrange
        ProductRequest productRequest =
                new ProductRequest("title", "description", "category", "10");
        productRequestValidator = new ProductRequestValidator(productRequest);

        // act
        Executable executable = productRequestValidator::validateRequest;

        // assert
        assertDoesNotThrow(executable);
    }
}
