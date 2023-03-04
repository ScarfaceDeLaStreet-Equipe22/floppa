package ulaval.glo2003.application;

import ulaval.glo2003.api.Utils.ItemNotFoundException;
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

        if (products.contains(product)){
            products.remove(product);
        } else {
            throw new ItemNotFoundException("Product not found");
        }
    }

    @Override
    public void deleteAll(){
        products.clear();
    }

    public Product findById(String id){
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new ItemNotFoundException("Product not found");
    }

    @Override
    public void update(Product product){
        boolean isProductFound = false;
        for (Product currentProduct : products) {
            if (currentProduct.getId().equals(product.getId())) {
                products.set(products.indexOf(currentProduct), product);
                isProductFound = true;
            }
        }
        if (!isProductFound) {
            throw new ItemNotFoundException("Product not found");
        }
    }

    @Override
    public ArrayList<Product> findAll(){
        return products;
    }
}
