package ulaval.glo2003.api.validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.api.exceptions.SellerRequestExceptions.*;
import ulaval.glo2003.api.requests.SellerRequest;

class SellerRequestValidatorTest {
    private SellerRequestValidator sellerRequestValidator;

    @Test
    public void givenNullName_whenValidatingRequest_thenThrowsMissingNameException() {
        // arrange
        SellerRequest requestWithNullName =
                new SellerRequest(null, "bio", "1995-01-01", "test@test.com", "18191234567");
        sellerRequestValidator = new SellerRequestValidator(requestWithNullName);

        // act
        Executable executable = sellerRequestValidator::validateRequest;

        // assert
        assertThrows(MissingNameException.class, executable);
    }

    @Test
    public void givenNullBio_whenValidatingRequest_thenThrowsMissingBioException() {
        // arrange
        SellerRequest requestWithNullBio =
                new SellerRequest("name", null, "1995-01-01", "test@test.com", "18191234567");
        sellerRequestValidator = new SellerRequestValidator(requestWithNullBio);

        // act
        Executable executable = sellerRequestValidator::validateRequest;

        // assert
        assertThrows(MissingBioException.class, executable);
    }

    @Test
    public void givenNullBirthDate_whenValidatingRequest_thenThrowsMissingBirthDateException() {
        // arrange
        SellerRequest requestWithNullBirthDate =
                new SellerRequest("name", "bio", null, "test@test.com", "18191234567");
        sellerRequestValidator = new SellerRequestValidator(requestWithNullBirthDate);

        // act
        Executable executable = sellerRequestValidator::validateRequest;

        // assert
        assertThrows(MissingBirthDateException.class, executable);
    }

    @Test
    public void givenNullEmail_whenValidatingRequest_thenThrowsMissingEmailException() {
        // arrange
        SellerRequest requestWithNullEmail =
                new SellerRequest("name", "bio", "1995-01-01", null, "18191234567");
        sellerRequestValidator = new SellerRequestValidator(requestWithNullEmail);

        // act
        Executable executable = sellerRequestValidator::validateRequest;

        // assert
        assertThrows(MissingEmailException.class, executable);
    }

    @Test
    public void givenNullPhoneNumber_whenValidatingRequest_thenThrowsMissingPhoneNumberException() {
        // arrange
        SellerRequest requestWithNullPhoneNumber =
                new SellerRequest("name", "bio", "1995-01-01", "test@test.com", null);
        sellerRequestValidator = new SellerRequestValidator(requestWithNullPhoneNumber);

        // act
        Executable executable = sellerRequestValidator::validateRequest;

        // assert
        assertThrows(MissingPhoneNumberException.class, executable);
    }

    @Test
    public void givenValidRequest_whenValidatingRequest_thenDoesNotThrowException() {
        // arrange
        SellerRequest validRequest =
                new SellerRequest("name", "bio", "1995-01-01", "test@test.com", "18191234567");
        sellerRequestValidator = new SellerRequestValidator(validRequest);

        // act
        Executable executable = sellerRequestValidator::validateRequest;

        // assert
        assertDoesNotThrow(executable);
    }
}
