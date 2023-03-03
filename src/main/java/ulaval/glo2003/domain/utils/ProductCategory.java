package ulaval.glo2003.domain.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidCategoryException;

public class ProductCategory {
    private final String category;

    public ProductCategory(String category) {
        this.category = category;
        assertProductCategory();
    }

    public String getCategory() {
        return category;
    }

    private void assertProductCategory() {
        List<String> validCategories = Arrays.asList("Sport", "electronics", "apparel", "beauty", "housing", "other");
        if(!validCategories.contains(category)) {
            throw new InvalidCategoryException();
        }
    }
}
