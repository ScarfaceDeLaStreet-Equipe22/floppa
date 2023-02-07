package ulaval.glo2003.domain.ProductClasses;

import java.util.ArrayList;
import ulaval.glo2003.api.ProductExceptions.InvalidCategoryException;

public class ProductCategory {
    private String category;

    public ProductCategory(String category) {

        if (productCategoryValidator(category)) {
            this.category = category;
        } else {
            throw new InvalidCategoryException();
        }
    }

    public String getCategory() {
        return category;
    }

    private boolean productCategoryValidator(String category) {
        ArrayList<String> validCategory = new ArrayList<>();
        boolean valide = false;
        validCategory.add("Sport");
        validCategory.add("electronics");
        validCategory.add("apparel");
        validCategory.add("beauty");
        validCategory.add("housing");
        validCategory.add("other");
        for (String nom : validCategory) {
            if (category.equals(nom)) {
                valide = true;
            }
        }
        return valide;
    }
}
