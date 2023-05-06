package ulaval.glo2003.api.mappers;

import ulaval.glo2003.api.requests.BuyerRequest;
import ulaval.glo2003.api.responses.BuyerResponse;
import ulaval.glo2003.api.validators.BuyerRequestValidator;
import ulaval.glo2003.domain.entities.Buyer;
import ulaval.glo2003.domain.utils.ProductCategory;
import ulaval.glo2003.domain.validators.BuyerValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                buyer.getCreatedAt(),
                buyer.preferences.stream().map(cat -> cat.category).collect(Collectors.toList()),
                buyer.purchases.stream().map(cat -> cat.category).collect(Collectors.toList()));
    }

    public Buyer mapRequestToEntity(BuyerRequest buyerRequest) {
        BuyerRequestValidator buyerRequestValidator = new BuyerRequestValidator(buyerRequest);
        buyerRequestValidator.validateRequest();

        List<ProductCategory> preferences = buyerRequest.preferences == null
                ? new ArrayList<>()
                : buyerRequest.preferences.stream().map(ProductCategory::new).collect(Collectors.toList());

        List<ProductCategory> purchases = buyerRequest.purchases == null
                ? new ArrayList<>()
                : buyerRequest.purchases.stream().map(ProductCategory::new).collect(Collectors.toList());
        Buyer buyer =
                new Buyer(
                        buyerRequest.getName(),
                        buyerRequest.getBirthdate(),
                        buyerRequest.getEmail(),
                        buyerRequest.getPhoneNumber(),
                        preferences,
                        purchases);

        BuyerValidator buyerValidator = new BuyerValidator(buyer);
        buyerValidator.validateEntity();

        return buyer;
    }
}
