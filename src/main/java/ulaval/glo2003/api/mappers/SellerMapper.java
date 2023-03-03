package ulaval.glo2003.api.mappers;

import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.SellerResponse;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.validators.SellerValidator;

public class SellerMapper {

    public SellerResponse mapEntityToResponse(Seller seller) {
        return new SellerResponse(
                seller.getId(),
                seller.getName(),
                seller.getBio(),
                seller.getBirthdate().getDate(),
                seller.getEmail(),
                seller.getPhoneNumber(),
                seller.getProducts(),
                seller.getCreatedAt());
    }

    public Seller mapRequestToEntity(SellerRequest sellerRequest) {
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
