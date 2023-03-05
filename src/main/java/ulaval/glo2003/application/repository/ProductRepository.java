package ulaval.glo2003.application.repository;

import java.util.ArrayList;
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
        try {
            products.remove(product);
        } catch (Exception e) {
            throw new ItemNotFoundException("Product not found");
        }
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
