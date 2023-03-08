package ulaval.glo2003.domain.validators;

import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.InvalidNameException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBioException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBirthdateException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidEmailException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidPhoneNumberException;

public class SellerValidator implements IValidator {

    private final Seller seller;

    public SellerValidator(Seller seller) {
        this.seller = seller;
    }

    @Override
    public void validateEntity() {
        assertParamNotEmpty();
        assertAge();
    }

    private void assertParamNotEmpty() {
        if (seller.getName().isEmpty()) {
            throw new InvalidNameException();
        }
        if (seller.getBio().isEmpty()) {
            throw new InvalidBioException();
        }
        if (seller.getBirthdate().toString().isEmpty()) {
            throw new InvalidBirthdateException();
        }
        if (seller.getEmail().isEmpty()) {
            throw new InvalidEmailException();
        }
        if (seller.getPhoneNumber().isEmpty()) {
            throw new InvalidPhoneNumberException();
        }
    }

    private void assertAge() {
        if (seller.getBirthdate().getYearsBetweenNow() < 18) {
            throw new InvalidBirthdateException();
        }
    }
}
