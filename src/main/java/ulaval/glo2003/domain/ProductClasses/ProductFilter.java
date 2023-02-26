package ulaval.glo2003.domain.ProductClasses;

import jakarta.ws.rs.QueryParam;
import jdk.jfr.Category;
import ulaval.glo2003.domain.Product;

import java.util.Optional;

public class ProductFilter
{
    private String sellerId;
    private String title;
    private String categoryName;
    private String minPrice;
    private String maxPrice;

    public ProductFilter(String sellerId, String title, String categoryName, String minPrice, String maxPrice) {
        this.sellerId = sellerId;
        this.title = title;
        this.categoryName = categoryName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public boolean checkProduct(Product product)
    {
        if(!checkProductSeller(product))
            return false;

        if(!checkProductTitle(product))
            return false;

        if(!checkProductCategory(product))
            return false;

        return true;
    }

    private boolean checkProductSeller(Product product)
    {
        if(sellerId == null)
            return true;

        return product.getSeller().getId().equals(sellerId);
    }

    private boolean checkProductTitle(Product product)
    {
        if(title == null)
            return true;

        return product.getTitle().toLowerCase().contains(title.toLowerCase());
    }

    private boolean checkProductCategory(Product product)
    {
        if(categoryName == null)
            return true;

        return product.getCategory().equals(categoryName);
    }
}
