package ulaval.glo2003.api.Mappers;

import ulaval.glo2003.api.Seller.SellerRequest;
import ulaval.glo2003.api.Seller.SellerResponse;
import ulaval.glo2003.domain.Seller;

public class SellerMapper {

    public SellerResponse mapEntityToResponse(Seller seller) {
        return new SellerResponse(
                seller.getId(),
                seller.getName(),
                seller.getBio(),
                seller.getBirthDate(),
                seller.getEmail(),
                seller.getPhoneNumber(),
                seller.getProducts(),
                seller.getCreatedAt());
    }


    public Seller mapRequestToEntity(SellerRequest sellerRequest) {
        return new Seller(
                sellerRequest.getName(),
                sellerRequest.getBirthdate(),
                sellerRequest.getEmail(),
                sellerRequest.getPhoneNumber(),
                sellerRequest.getBio());
    }
}
