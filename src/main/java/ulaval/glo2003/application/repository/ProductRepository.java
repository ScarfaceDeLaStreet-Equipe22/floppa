package ulaval.glo2003.application.repository;

import java.util.ArrayList;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.exceptions.ItemNotFoundException;

public class ProductRepository implements IRepository<Product> {

    private final ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void remove(Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product itProduct = products.get(i);
            if (product.getId().equals(itProduct.getId())) {
                products.remove(i);
                return;
            }
        }

        throw new ItemNotFoundException("Product not found");
    }

    @Override
    public void deleteAll() {
        products.clear();
    }

    public Product findById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new ItemNotFoundException("Product not found");
    }

    @Override
    public void update(Product product) {
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
    public ArrayList<Product> findAll() {
        return products;
    }

    @Override
    public int count() {
        return products.size();
    }
}
