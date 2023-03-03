package ulaval.glo2003.api.mappers;

import ulaval.glo2003.api.requests.OfferRequest;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.validators.OfferValidator;

public class OfferMapper {

    public Offer mapRequestToEntity(
            OfferRequest offerRequest, String BuyerUsername, Product product) {
        Offer offer =
                new Offer(
                        new Amount(offerRequest.getAmount()),
                        offerRequest.getMessage(),
                        BuyerUsername);
        OfferValidator offerValidator = new OfferValidator(offer, product);
        offerValidator.validateEntity();
        return offer;
    }
}
