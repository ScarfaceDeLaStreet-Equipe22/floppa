package ulaval.glo2003.domain.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.InvalidNameException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBioException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBirthdateException;
import ulaval.glo2003.domain.utils.Date;


import static org.junit.jupiter.api.Assertions.*;

public class SellerValidatorTest {

    private final static String VALID_DATE = "01-01-1995";
    private final static String VALID_NAME = "name";
    private final static String VALID_EMAIL = "john.doe@example.com";
    private final static String VALID_PHONENUMBER = "18191234567";
    private final static String VALID_BIO = "bio";

    @Test
    public void givenSellerWithEmptyName_whenValidatingEntity_thenThrowsInvalidNameException() {
        //arrange
        Seller sellerWithEmptyName = new Seller("", VALID_DATE, VALID_EMAIL, VALID_PHONENUMBER, VALID_BIO);
        SellerValidator sellerValidator = new SellerValidator(sellerWithEmptyName);

        //act
        Executable executable = sellerValidator::validateEntity;

        //assert
        assertThrows(InvalidNameException.class, executable);
    }

    @Test
    public void givenSellerWithEmptyBio_whenValidatingEntity_thenThrowsInvalidBioException() {
        //arrange
        Seller sellerWithEmptyBio = new Seller(VALID_NAME, VALID_DATE, VALID_EMAIL, VALID_PHONENUMBER, "");
        SellerValidator sellerValidator = new SellerValidator(sellerWithEmptyBio);

        //act
        Executable executable = sellerValidator::validateEntity;

        //assert
        assertThrows(InvalidBioException.class, executable);
    }

    @Test
    public void givenSellerWithBirthdateLessThan18YearsOld_whenValidating(){
        //arrange
        Seller sellerWithLessThan18YearBirthdate = new Seller(VALID_NAME, Date.getFormattedCurrentDate(), VALID_EMAIL, VALID_PHONENUMBER, VALID_BIO);
        SellerValidator sellerValidator = new SellerValidator(sellerWithLessThan18YearBirthdate);

        //act
        Executable executable = sellerValidator::validateEntity;

        //assert
        assertThrows(InvalidBirthdateException.class, executable);
    }
}