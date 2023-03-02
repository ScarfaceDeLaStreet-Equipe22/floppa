package ulaval.glo2003.api.Validators;

import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Seller.SellerRequest;
import ulaval.glo2003.api.SellerExceptions.*;
import ulaval.glo2003.api.Utils.InvalidNameException;
import ulaval.glo2003.api.Utils.MissingNameException;
import ulaval.glo2003.domain.SellerClasses.Date;

public class SellerRequestValidator{

    private final SellerRequest sellerRequest;

    public SellerRequestValidator(SellerRequest sellerRequest){
        this.sellerRequest = sellerRequest;
    }

    public void validateRequest() {
        assertParamNotNull();
        assertParamNotEmpty();
        assertAge();
    }


    private void assertParamNotNull() {
        if (sellerRequest.getName().isEmpty()) {
            throw new InvalidNameException();
        }
        if (sellerRequest.getBio().isEmpty()) {
            throw new InvalidBioException();
        }
        if (sellerRequest.getBirthdate().isEmpty()) {
            throw new InvalidBirthdateException();
        }
        if (sellerRequest.getEmail().isEmpty()) {
            throw new InvalidEmailException();
        }
        if (sellerRequest.getPhoneNumber().isEmpty()) {
            throw new InvalidPhoneNumberException();
        }
    }

    private void assertParamNotEmpty() {
        if (sellerRequest.getName() == null) {
            throw new MissingNameException();
        }
        if (sellerRequest.getBio() == null) {
            throw new MissingBioException();
        }
        if (sellerRequest.getBirthdate() == null) {
            throw new MissingBirthDateException();
        }
        if (sellerRequest.getEmail() == null) {
            throw new MissingEmailException();
        }
        if (sellerRequest.getPhoneNumber() == null) {
            throw new MissingPhoneNumberException();
        }
    }

    private void assertAge(){
        Date birthdate = new Date(sellerRequest.getBirthdate());
        birthdate.assertAge();
    }
}
