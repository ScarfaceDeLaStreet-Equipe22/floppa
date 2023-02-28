package ulaval.glo2003.api.Mappers;

import ulaval.glo2003.api.Offer.OfferRequest;
import ulaval.glo2003.api.Offer.OfferResponse;
import ulaval.glo2003.domain.Offer;

public class OfferMapper implements IMapper<Offer, OfferResponse, OfferRequest>{
    @Override
    public OfferResponse mapEntitytoResponse(Offer offer) {
        return null;
    }

    @Override
    public Offer mapResponsetoEntity(OfferRequest offerRequest) {
        return null;
    }
}
