package ulaval.glo2003.application.repository;

import ulaval.glo2003.domain.entities.Buyer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;
import ulaval.glo2003.domain.utils.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class BuyerRepository implements IRepository<Buyer>{

    private final ArrayList<Buyer> buyers;

    public List<ProductCategory> preferences;

    public BuyerRepository() {
        this.buyers = new ArrayList<>();
    }

    @Override
    public void save(Buyer buyer) {
        buyers.add(buyer);
    }

    @Override
    public void remove(Buyer buyer) {
        for (int i = 0; i < buyers.size(); i++) {
            Buyer itBuyer = buyers.get(i);
            if (buyer.getId().equals(itBuyer.getId())) {
                buyers.remove(i);
                return;
            }
        }

        throw new ItemNotFoundException("Buyer not found");
    }

    @Override
    public void deleteAll() {
        buyers.clear();
    }

    public Buyer findById(String id) {
        for (Buyer buyer : buyers) {
            if (buyer.getId().equals(id)) {
                return buyer;
            }
        }
        throw new ItemNotFoundException("Buyer not found");
    }

    @Override
    public void update(Buyer buyer) {
        boolean isBuyerFound = false;
        for (Buyer currentBuyer : buyers) {
            if (currentBuyer.getId().equals(buyer.getId())) {
                buyers.set(buyers.indexOf(currentBuyer), buyer);
                isBuyerFound = true;
            }
        }
        if (!isBuyerFound) {
            throw new ItemNotFoundException("Buyer not found");
        }
    }

    @Override
    public ArrayList<Buyer> findAll() {
        return buyers;
    }

    @Override
    public int count() {
        return buyers.size();
    }
}
