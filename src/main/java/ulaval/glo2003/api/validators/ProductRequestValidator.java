package ulaval.glo2003.api.validators;

import ulaval.glo2003.api.exceptions.ProductRequestExceptions.*;
import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidSuggestedPriceException;

public class ProductRequestValidator implements IValidatorRequest {

    private final ProductRequest productRequest;

    public ProductRequestValidator(ProductRequest productRequest) {
        this.productRequest = productRequest;
    }

    @Override
    public void validateRequest() {
        assertParamNotNull();
        amountValidation(productRequest.getSuggestedPrice());
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

    private void amountValidation(String amount) {
        if (amount != null) {
            if (amount.isEmpty()) {
                throw new InvalidSuggestedPriceException();
            } else {
                try {
                    double amountDouble = Double.parseDouble(amount);
                } catch (Exception e){
                    throw new InvalidSuggestedPriceException() ;
                }
            }
        } else {
            throw new MissingSuggestedPriceException() ;
        }
    }
}
