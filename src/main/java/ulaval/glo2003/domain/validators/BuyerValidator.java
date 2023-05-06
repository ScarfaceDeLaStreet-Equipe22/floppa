package ulaval.glo2003.domain.validators;

import ulaval.glo2003.domain.entities.Buyer;
import ulaval.glo2003.domain.exceptions.InvalidNameException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidBirthdateException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidEmailException;
import ulaval.glo2003.domain.exceptions.SellerExceptions.InvalidPhoneNumberException;

public class BuyerValidator implements IValidator {

    private final Buyer buyer;

    public BuyerValidator(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void validateEntity() {
        assertParamNotEmpty();
        assertAge();
    }

    private void assertParamNotEmpty() {
        if (buyer.getName().isEmpty()) {
            throw new InvalidNameException();
        }
        if (buyer.getBirthdate().toString().isEmpty()) {
            throw new InvalidBirthdateException();
        }
        if (buyer.getEmail().isEmpty()) {
            throw new InvalidEmailException();
        }
        if (buyer.getPhoneNumber().isEmpty()) {
            throw new InvalidPhoneNumberException();
        }
    }

    private void assertAge() {
        if (buyer.getBirthdate().getYearsBetweenNow() < 18) {
            throw new InvalidBirthdateException();
        }
    }

}
