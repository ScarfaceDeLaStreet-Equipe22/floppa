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
        SellerRequest requestWithNullName =
                new SellerRequest(null, "bio", "1995-01-01", "test@test.com", "18191234567");
        sellerRequestValidator = new SellerRequestValidator(requestWithNullName);

        Executable executable = sellerRequestValidator::validateRequest;

        assertThrows(MissingNameException.class, executable);
    }

    @Test
    public void givenNullBio_whenValidatingRequest_thenThrowsMissingBioException() {
        SellerRequest requestWithNullBio =
                new SellerRequest("name", null, "1995-01-01", "test@test.com", "18191234567");
        sellerRequestValidator = new SellerRequestValidator(requestWithNullBio);

        Executable executable = sellerRequestValidator::validateRequest;

        assertThrows(MissingBioException.class, executable);
    }

    @Test
    public void givenNullBirthDate_whenValidatingRequest_thenThrowsMissingBirthDateException() {
        SellerRequest requestWithNullBirthDate =
                new SellerRequest("name", "bio", null, "test@test.com", "18191234567");
        sellerRequestValidator = new SellerRequestValidator(requestWithNullBirthDate);

        Executable executable = sellerRequestValidator::validateRequest;

        assertThrows(MissingBirthDateException.class, executable);
    }

    @Test
    public void givenNullEmail_whenValidatingRequest_thenThrowsMissingEmailException() {
        SellerRequest requestWithNullEmail =
                new SellerRequest("name", "bio", "1995-01-01", null, "18191234567");
        sellerRequestValidator = new SellerRequestValidator(requestWithNullEmail);

        Executable executable = sellerRequestValidator::validateRequest;

        assertThrows(MissingEmailException.class, executable);
    }

    @Test
    public void givenNullPhoneNumber_whenValidatingRequest_thenThrowsMissingPhoneNumberException() {
        SellerRequest requestWithNullPhoneNumber =
                new SellerRequest("name", "bio", "1995-01-01", "test@test.com", null);
        sellerRequestValidator = new SellerRequestValidator(requestWithNullPhoneNumber);

        Executable executable = sellerRequestValidator::validateRequest;

        assertThrows(MissingPhoneNumberException.class, executable);
    }

    @Test
    public void givenValidRequest_whenValidatingRequest_thenDoesNotThrowException() {
        SellerRequest validRequest =
                new SellerRequest("name", "bio", "1995-01-01", "test@test.com", "18191234567");
        sellerRequestValidator = new SellerRequestValidator(validRequest);

        Executable executable = sellerRequestValidator::validateRequest;

        assertDoesNotThrow(executable);
    }
}
