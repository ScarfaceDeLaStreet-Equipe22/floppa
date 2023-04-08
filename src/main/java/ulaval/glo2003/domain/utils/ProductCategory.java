package ulaval.glo2003.domain.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidCategoryException;
@Entity
public class ProductCategory {

    public String category;
    @Id
    public String id;

    public ProductCategory(){}

    public ProductCategory(String category) {
        this.category = category;
        this.id = UUID.randomUUID().toString();
        assertProductCategory();
    }

    public String getCategory() {
        return category;
    }

    private void assertProductCategory() {
        List<String> validCategories =
                Arrays.asList("Sport", "electronics", "apparel", "beauty", "housing", "other");
        if (!validCategories.contains(category)) {
            throw new InvalidCategoryException();
        }
    }
}
