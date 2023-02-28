package ulaval.glo2003.api.Validators;

import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Seller.SellerRequest;
import ulaval.glo2003.api.SellerExceptions.*;
import ulaval.glo2003.api.Utils.InvalidNameException;
import ulaval.glo2003.api.Utils.MissingNameException;

public class SellerRequestValidator implements IValidator<SellerRequest>{

    private final SellerRequest sellerRequest;

    public SellerRequestValidator(SellerRequest sellerRequest){
        this.sellerRequest = sellerRequest;
    }
    @Override
    public void validateRequest() {
        assertParamNotNull(sellerRequest);
        assertParamNotEmpty(sellerRequest);
    }

    @Override
    public void assertParamNotNull(SellerRequest sellerRequest) {
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

    @Override
    public void assertParamNotEmpty(SellerRequest sellerRequest) {
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
}
