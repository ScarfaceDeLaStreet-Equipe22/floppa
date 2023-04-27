package ulaval.glo2003.api.mappers;

import ulaval.glo2003.api.requests.BuyerRequest;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.*;
import ulaval.glo2003.api.validators.BuyerRequestValidator;
import ulaval.glo2003.api.validators.SellerRequestValidator;
import ulaval.glo2003.domain.entities.Buyer;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.validators.BuyerValidator;
import ulaval.glo2003.domain.validators.SellerValidator;

import java.util.ArrayList;

public class BuyerMapper {

    public BuyerResponse mapEntityToResponse(Buyer buyer) {
        BuyerValidator buyerValidator = new BuyerValidator(buyer);
        buyerValidator.validateEntity();

        return new BuyerResponse(
                buyer.getId(),
                buyer.getName(),
                buyer.getBirthdate().getDate(),
                buyer.getEmail(),
                buyer.getPhoneNumber(),
                buyer.getCreatedAt());
    }

    public Buyer mapRequestToEntity(BuyerRequest buyerRequest) {
        BuyerRequestValidator buyerRequestValidator = new BuyerRequestValidator(buyerRequest);
        buyerRequestValidator.validateRequest();

        Buyer buyer =
                new Buyer(
                        buyerRequest.getName(),
                        buyerRequest.getBirthdate(),
                        buyerRequest.getEmail(),
                        buyerRequest.getPhoneNumber());

        BuyerValidator buyerValidator = new BuyerValidator(buyer);
        buyerValidator.validateEntity();

        return buyer;
    }
}
