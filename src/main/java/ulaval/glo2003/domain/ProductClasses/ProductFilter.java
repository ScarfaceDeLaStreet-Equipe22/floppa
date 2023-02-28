package ulaval.glo2003.domain.ProductClasses;

import ulaval.glo2003.domain.Product;

public class ProductFilter {
    private String sellerId;
    private String title;
    private String categoryName;
    private Amount minPrice;
    private Amount maxPrice;

    public ProductFilter(
            String sellerId, String title, String categoryName, String minPrice, String maxPrice) {
        this.sellerId = sellerId;
        this.title = title;
        this.categoryName = categoryName;
        this.minPrice = minPrice == null ? null : new Amount(minPrice);
        this.maxPrice = maxPrice == null ? null : new Amount(maxPrice);
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
        if (categoryName == null) return true;

        return product.getCategory().equals(categoryName);
    }

    private boolean checkProductMinPrice(Product product) {
        if (minPrice == null) return true;

        return product.suggestedPrice.getAmount() >= minPrice.getAmount();
    }

    private boolean checkProductMaxPrice(Product product) {
        if (maxPrice == null) return true;

        return product.suggestedPrice.getAmount() <= maxPrice.getAmount();
    }
}
