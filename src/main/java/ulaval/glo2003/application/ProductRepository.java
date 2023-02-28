package ulaval.glo2003.application;

import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;
import ulaval.glo2003.domain.Product;

import java.util.ArrayList;

public class ProductRepository implements IRepository<Product>{

    private final ArrayList<Product> products;

    public ProductRepository(){
        this.products = new ArrayList<>();
    }
    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void remove(Product product) {
        products.remove(product);
    }

    @Override
    public void deleteAll(){
        products.clear();
    }

    @Override
    public Product findById(String id){
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new ItemNotFoundException();
    }

    @Override
    public void update(Product product){

    }

    @Override
    public ArrayList<Product> findAll(){
        return products;
    }
}
