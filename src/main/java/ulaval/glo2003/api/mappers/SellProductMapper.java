package ulaval.glo2003.api.mappers;

import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.validators.SellerRequestValidator;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.validators.SellerValidator;

public class SellProductMapper {

    public Seller mapRequestToEntity(SellerRequest sellerRequest) {
        SellerRequestValidator sellerRequestValidator = new SellerRequestValidator(sellerRequest);
        sellerRequestValidator.validateRequest();

        Seller seller =
                new Seller(
                        sellerRequest.getName(),
                        sellerRequest.getBirthdate(),
                        sellerRequest.getEmail(),
                        sellerRequest.getPhoneNumber(),
                        sellerRequest.getBio());
        SellerValidator sellerValidator = new SellerValidator(seller);
        sellerValidator.validateEntity();
        return seller;
    }
}
