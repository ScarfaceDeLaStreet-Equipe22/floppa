package ulaval.glo2003.domain.validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.InvalidNameException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBioException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBirthdateException;
import ulaval.glo2003.domain.utils.Date;

public class SellerValidatorTest {

    private static final String VALID_DATE = "01-01-1995";
    private static final String VALID_NAME = "name";
    private static final String VALID_EMAIL = "john.doe@example.com";
    private static final String VALID_PHONENUMBER = "18191234567";
    private static final String VALID_BIO = "bio";

    @Test
    public void givenSellerWithEmptyName_whenValidatingEntity_thenThrowsInvalidNameException() {
        Seller sellerWithEmptyName =
                new Seller("", VALID_DATE, VALID_EMAIL, VALID_PHONENUMBER, VALID_BIO);
        SellerValidator sellerValidator = new SellerValidator(sellerWithEmptyName);

        Executable executable = sellerValidator::validateEntity;

        assertThrows(InvalidNameException.class, executable);
    }

    @Test
    public void givenSellerWithEmptyBio_whenValidatingEntity_thenThrowsInvalidBioException() {
        Seller sellerWithEmptyBio =
                new Seller(VALID_NAME, VALID_DATE, VALID_EMAIL, VALID_PHONENUMBER, "");
        SellerValidator sellerValidator = new SellerValidator(sellerWithEmptyBio);

        Executable executable = sellerValidator::validateEntity;

        assertThrows(InvalidBioException.class, executable);
    }

    @Test
    public void givenSellerWithBirthdateLessThan18YearsOld_whenValidating() {
        Seller sellerWithLessThan18YearBirthdate =
                new Seller(
                        VALID_NAME,
                        Date.getFormattedCurrentDate(),
                        VALID_EMAIL,
                        VALID_PHONENUMBER,
                        VALID_BIO);
        SellerValidator sellerValidator = new SellerValidator(sellerWithLessThan18YearBirthdate);

        Executable executable = sellerValidator::validateEntity;

        assertThrows(InvalidBirthdateException.class, executable);
    }
}
