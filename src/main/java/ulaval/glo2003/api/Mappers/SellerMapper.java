package ulaval.glo2003.api.Mappers;

import ulaval.glo2003.api.Seller.SellerRequest;
import ulaval.glo2003.api.Seller.SellerResponse;
import ulaval.glo2003.domain.Seller;

public class SellerMapper implements  IMapper<Seller, SellerResponse, SellerRequest>{
    @Override
    public SellerResponse mapEntitytoResponse(Seller seller) {
        return null;
    }

    @Override
    public Seller mapResponsetoEntity(SellerRequest sellerRequest) {
        return null;
    }
}
