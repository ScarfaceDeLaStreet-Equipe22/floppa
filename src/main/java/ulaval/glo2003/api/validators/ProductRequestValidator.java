package ulaval.glo2003.api.validators;

import ulaval.glo2003.api.exceptions.ProductRequestExceptions.*;
import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidCategoryException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidDescriptionException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidTitleException;

public class ProductRequestValidator implements IValidatorRequest {

    private final ProductRequest productRequest;

    public ProductRequestValidator(ProductRequest productRequest) {
        this.productRequest = productRequest;
    }

    @Override
    public void validateRequest() {
        assertParamNotNull();
        assertParamNotEmpty();
    }

    private void assertParamNotNull() {
        if (productRequest.getTitle() == null) {
            throw new MissingTitleException();
        }
        if (productRequest.getDescription() == null) {
            throw new MissingDescriptionException();
        }
        if (productRequest.getCategory() == null) {
            throw new MissingCategoryException();
        }
        if (productRequest.getSuggestedPrice() == null) {
            throw new MissingSuggestedPriceException();
        }
    }

    private void assertParamNotEmpty() {
        if (productRequest.getTitle().isEmpty()) {
            throw new InvalidTitleException();
        }
        if (productRequest.getDescription().isEmpty()) {
            throw new InvalidDescriptionException();
        }
        if (productRequest.getCategory().isEmpty()) {
            throw new InvalidCategoryException();
        }
    }
}
