package ulaval.glo2003.application;

import ulaval.glo2003.api.Utils.ItemNotFoundException;
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
            throw new ItemNotFoundException("Offer not found");
        }
    }

    @Override
    public void deleteAll() {
        offers.clear();
    }


    @Override
    public ArrayList<Offer> findAll() {
        return offers;
    }
}
