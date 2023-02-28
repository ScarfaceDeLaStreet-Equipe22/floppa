package ulaval.glo2003.api.Validators;

import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.ProductExceptions.*;
import ulaval.glo2003.domain.ProductClasses.Amount;

public class ProductRequestValidator implements IValidator<ProductRequest>{

    private final ProductRequest productRequest;
    public ProductRequestValidator(ProductRequest productRequest){
        this.productRequest = productRequest;
    }
    @Override
    public void validateRequest() {
        assertParamNotEmpty(productRequest);
        assertParamNotNull(productRequest);
    }

    @Override
    public void assertParamNotNull(ProductRequest productRequest) {
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
            // Il faut merge le fix à Nabil
        }
    }

    @Override
    public void assertParamNotEmpty(ProductRequest productRequest) {
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
