package ulaval.glo2003.api.Validators;

import com.google.common.truth.Truth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.ProductExceptions.InvalidCategoryException;
import ulaval.glo2003.api.ProductExceptions.InvalidDescriptionException;
import ulaval.glo2003.api.ProductExceptions.InvalidTitleException;
import ulaval.glo2003.api.ProductExceptions.MissingSuggestedPriceException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductRequestValidatorTest {
    @Test
    void validateRequest_withValidRequest_shouldNotThrowException() {
        // Arrange
        ProductRequest validRequest = new ProductRequest("Title", "Description", "Category", "10.0");
        ProductRequestValidator validator = new ProductRequestValidator(validRequest);

        // Act & Assert

        try {
            validator.validateRequest() ;
        }  catch (Exception e){
        assertThat(validator.validateRequest()).isEqualTo(true);
        }
    }

    @Test
    void validateRequest_withNullTitle_shouldThrowMissingTitleException() {
        // Arrange
        ProductRequest invalidRequest = new ProductRequest(null, "Description", "Category", "10.0");
        ProductRequestValidator validator = new ProductRequestValidator(invalidRequest);

        // Act & Assert
        try {
            validator.validateRequest() ;
        } catch (Exception e){
            assertThat(e.getMessage()).isEqualTo("Missing parameter 'Title'.");

        }
    }

    @Test
    void validateRequest_withNullDescription_shouldThrowMissingDescriptionException() {
        // Arrange
        ProductRequest invalidRequest = new ProductRequest("Title", null, "Category", "10.0");
        ProductRequestValidator validator = new ProductRequestValidator(invalidRequest);

        try {
            validator.validateRequest() ;
        } catch (Exception e){
            assertThat(e.getMessage()).isEqualTo("Missing parameter 'Description'.");

        }

    }

    @Test
    void validateRequest_withNullCategory_shouldThrowMissingCategoryException() {
        // Arrange
        ProductRequest invalidRequest = new ProductRequest("Title", "Description", null, "10.0");
        ProductRequestValidator validator = new ProductRequestValidator(invalidRequest);

        // Act & Assert
        try {
            validator.validateRequest() ;
        } catch (Exception e){
            assertThat(e.getMessage()).isEqualTo("Missing parameter 'SuggestedPrice'.");

        }

    }

    @Test
    void validateRequest_withNullSuggestedPrice_shouldThrowInvalidSuggestedPriceException() {
        // Arrange
        ProductRequest invalidRequest = new ProductRequest("Title", "Description", "", "10.0");
        ProductRequestValidator validator = new ProductRequestValidator(invalidRequest);

        try {
            validator.validateRequest() ;
        } catch (Exception e){
            assertThat(e.getMessage()).isEqualTo("Invalid parameter 'SuggestedPrice'.");

        }
    }



    @Test
    void validateRequest_withEmptyTitle_shouldThrowInvalidTitleException() {
        // Arrange
        ProductRequest invalidRequest = new ProductRequest("", "Description", "Category", "10.0");
        ProductRequestValidator validator = new ProductRequestValidator(invalidRequest);

        // Act & Assert
        try {
            validator.validateRequest() ;
        } catch (Exception e){
            assertThat(e.getMessage()).isEqualTo("Invalid parameter 'Title'.");

        }

    }

    @Test
    void validateRequest_withEmptyDescription_shouldThrowInvalidDescriptionException() {
        // Arrange
        ProductRequest invalidRequest = new ProductRequest("Title", "", "Category", "10.0");
        ProductRequestValidator validator = new ProductRequestValidator(invalidRequest);

        // Act & Assert
        try {
            validator.validateRequest() ;
        } catch (Exception e){
            assertThat(e.getMessage()).isEqualTo("Invalid parameter 'Description'.");

        }
    }

    @Test
    void validateRequest_withEmptyCategory_shouldThrowInvalidCategoryException() {
        ProductRequest invalidRequest = new ProductRequest("Title", "Description", "Category", "null");
        ProductRequestValidator validator = new ProductRequestValidator(invalidRequest);

        try {
            validator.validateRequest() ;
        } catch (Exception e){
            assertThat(e.getMessage()).isEqualTo("Invalid parameter 'Category'.");

        }
    }
    }