package ulaval.glo2003.domain.validators;

import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.exceptions.ProductExceptions.*;

public class ProductValidator implements IValidator {
    private final Product product;

    public ProductValidator(Product product) {
        this.product = product;
    }

    @Override
    public void validateEntity() {
        assertParamNotEmpty();
        assertSuggestedPrice();
    }

    private void assertParamNotEmpty() {
        if (product.getTitle().isEmpty()) {
            throw new InvalidTitleException();
        }
        if (product.getDescription().isEmpty()) {
            throw new InvalidDescriptionException();
        }
        if (product.getCategory().isEmpty()) {
            throw new InvalidCategoryException();
        }
    }

    private void assertSuggestedPrice() {
        if (product.getSuggestedPrice().toDouble() < 1) {
            throw new InvalidSuggestedPriceException();
        }
    }
}
