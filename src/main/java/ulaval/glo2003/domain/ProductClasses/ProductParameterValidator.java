package ulaval.glo2003.domain.ProductClasses;

import ulaval.glo2003.api.ProductExceptions.*;

public class ProductParameterValidator {
    public String title;
    public String description;
    public ProductCategory category;
    public Amount suggestedPrice;

    public ProductParameterValidator(
            String title, String description, ProductCategory category, Amount suggestedPrice) {
        assertParamNotNull(title, description, category, suggestedPrice);
        assertParamNotEmpty(title, description, category, suggestedPrice);
        assertSuggestedPrice(suggestedPrice);

        this.category = category;
        this.suggestedPrice = suggestedPrice;
        this.description = description;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Amount getSuggestedPrice() {
        return suggestedPrice;
    }

    private void assertParamNotNull(
            String title, String description, ProductCategory category, Amount suggestedPrice) {
        if (title == null) {
            throw new MissingTitleException();
        }
        if (description == null) {
            throw new MissingDescriptionException();
        }
        if (category == null) {
            throw new MissingCategoryException();
        }
        if (suggestedPrice == null) {
            this.suggestedPrice = new Amount("1.00");
        }
    }

    private void assertParamNotEmpty(String title, String description, ProductCategory category, Amount suggestedPrice) {

        if (title.isEmpty()) {
            throw new InvalidTitleException();
        }
        if (description.isEmpty()) {
            throw new InvalidDescriptionException();
        }
        if (category.getCategory().isEmpty()) {
            throw new InvalidCategoryException();
        }
    }

    private void assertSuggestedPrice(Amount suggestedPrice){
        if (suggestedPrice.getAmount() < 1){
            throw new InvalidSuggestedPriceException();
        }
    }
}
