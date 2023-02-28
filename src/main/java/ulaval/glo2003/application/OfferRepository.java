package ulaval.glo2003.application;

import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Offer;

import java.util.ArrayList;

public class OfferRepository implements IRepository<Offer>{

    private final ArrayList<Offer> offers;

    public OfferRepository(){
        this.offers = new ArrayList<>();
    }
    @Override
    public void save(Offer offer) {
        offers.add(offer);
    }

    @Override
    public void remove(Offer offer) {
        try{
            offers.remove(offer);
        }
        catch (Exception e)
        {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public void deleteAll() {
        offers.clear();
    }

    @Override
    public void update(Offer offer) {
        boolean isofferFound = false;
        for (Offer currentOffer : offers) {
            if (currentOffer.getBuyerUsername().equals(offer.getBuyerUsername())) {
                offers.set(offers.indexOf(currentOffer), offer);
                isofferFound = true;
            }
        }
        if (!isofferFound) {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public ArrayList<Offer> findAll() {
        return offers;
    }
}
