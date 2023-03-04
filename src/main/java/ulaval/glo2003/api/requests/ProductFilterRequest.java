package ulaval.glo2003.api.requests;

import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

public class ProductFilterRequest {
    private final String sellerId;
    private final String title;
    private final ProductCategory category;
    private final Amount minPrice;
    private final Amount maxPrice;

    public ProductFilterRequest(
            String sellerId, String title, ProductCategory category, Amount minPrice, Amount maxPrice) {
        this.sellerId = sellerId;
        this.title = title;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public boolean checkProduct(Product product) {
        if (!checkProductSeller(product)) return false;

        if (!checkProductTitle(product)) return false;

        if (!checkProductCategory(product)) return false;

        if (!checkProductMinPrice(product)) return false;

        if (!checkProductMaxPrice(product)) return false;

        return true;
    }

    private boolean checkProductSeller(Product product) {
        if (sellerId == null) return true;

        return product.getSeller().getId().equals(sellerId);
    }

    private boolean checkProductTitle(Product product) {
        if (title == null) return true;

        return product.getTitle().toLowerCase().contains(title.toLowerCase());
    }

    private boolean checkProductCategory(Product product) {
        if (category == null) return true;

        return product.getCategory().equals(category.getCategory());
    }

    private boolean checkProductMinPrice(Product product) {
        if (minPrice == null) return true;

        return product.suggestedPrice.toDouble() >= minPrice.toDouble();
    }

    private boolean checkProductMaxPrice(Product product) {
        if (maxPrice == null) return true;

        return product.suggestedPrice.toDouble() <= maxPrice.toDouble();
    }
}
