package ulaval.glo2003.application.repository;

import java.util.ArrayList;
import ulaval.glo2003.api.exceptions.ProductRequestExceptions.MissingSellerIdException;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;

public class SellerRepository implements IRepository<Seller> {

    private final ArrayList<Seller> sellers;

    public SellerRepository() {
        this.sellers = new ArrayList<>();
    }

    @Override
    public void save(Seller seller) {
        sellers.add(seller);
    }

    @Override
    public void remove(Seller seller) {
        try {
            sellers.remove(seller);
        } catch (Exception e) {
            throw new ItemNotFoundException("seller not found");
        }
    }

    @Override
    public void deleteAll() {
        sellers.clear();
    }

    public Seller findById(String id) {
        return sellers.stream()
                .filter(seller -> seller.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MissingSellerIdException());
    }

    @Override
    public void update(Seller seller) {
        boolean isSellerFound = false;
        for (Seller currentSeller : sellers) {
            if (currentSeller.getId().equals(seller.getId())) {
                sellers.set(sellers.indexOf(currentSeller), seller);
                isSellerFound = true;
            }
        }
        if (!isSellerFound) {
            throw new ItemNotFoundException("seller not found");
        }
    }

    @Override
    public ArrayList<Seller> findAll() {
        return sellers;
    }

    @Override
    public int count() {
        return sellers.size();
    }
}
