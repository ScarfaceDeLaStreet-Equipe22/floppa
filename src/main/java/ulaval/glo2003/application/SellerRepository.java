package ulaval.glo2003.application;

import ulaval.glo2003.api.ProductExceptions.ItemNotFoundSellerIdException;
import ulaval.glo2003.api.ProductExceptions.MissingSellerIdException;
import ulaval.glo2003.api.Utils.ItemNotFoundException;
import ulaval.glo2003.domain.Seller;

import java.util.ArrayList;

public class SellerRepository implements IRepository<Seller>{

    private final ArrayList<Seller> sellers;

    public SellerRepository(){
        this.sellers = new ArrayList<>();
    }
    @Override
    public void save(Seller seller) {
        sellers.add(seller);
    }

    @Override
    public void remove(Seller seller) {
        try{
            sellers.remove(seller);
        }
        catch (Exception e)
        {
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
    public ArrayList<Seller> findAll() {
        return sellers;
    }
}
