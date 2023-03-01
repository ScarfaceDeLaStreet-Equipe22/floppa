package ulaval.glo2003.api.Mappers;

import ulaval.glo2003.api.Offer.OfferRequest;
import ulaval.glo2003.api.Offer.OfferResponse;
import ulaval.glo2003.domain.Offer;

public class OfferMapper{

    public OfferMapper(){}
    public Offer mapRequestToEntity(OfferRequest offerRequest, String BuyerUsername) {
        return new Offer(
                offerRequest.getAmount(),
                offerRequest.getMessage(),
                BuyerUsername);
    }
}
