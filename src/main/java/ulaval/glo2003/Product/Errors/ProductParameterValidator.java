package ulaval.glo2003.Product.Errors;

import ulaval.glo2003.Seller.MissingBioException;
import ulaval.glo2003.Seller.MissingBirthDateException;

public class ProductParameterValidator {
    public String title;
    public String description;
    public String category;
    public double suggestedPrice;

    public ProductParameterValidator(String title, String description, String category, String suggestedPrice) {
        assertParamNotNull(title, description,category,suggestedPrice);
        this.category = category;
        this.suggestedPrice = Double.parseDouble(suggestedPrice) ;
        this.description = description ;
        this.title = title;
    }

    private void assertParamNotNull(String title, String description, String category, String suggestedPrice) {
        if (title == null) {
            throw new MissingTitleException();
        }
        if ( description == null) {
            throw new MissingDescriptionException();
        }
        if (category == null) {
            throw new MissingCategoryException();
        }
        if (suggestedPrice == null) {
            throw new MissingSuggestedPriceException();
        }
    }
}
