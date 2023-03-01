package ulaval.glo2003.api.Validators;

import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.ProductExceptions.*;

public class ProductRequestValidator{

    private final ProductRequest productRequest;
    public ProductRequestValidator(ProductRequest productRequest){
        this.productRequest = productRequest;
    }

    public void validateRequest() {
        assertParamNotNull();
        assertParamNotEmpty();
    }


    public void assertParamNotNull() {
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


    public void assertParamNotEmpty() {
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
