package ulaval.glo2003.api.mappers;

import ulaval.glo2003.api.requests.ProductFilterRequest;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

public class ProductFilterMapper {

    public ProductFilterRequest mapQueryParamsToRequest(String sellerId, String title, String categoryName, String minPrice, String maxPrice)
    {
        ProductCategory category = new ProductCategory(categoryName);
        Amount minPriceAmount = null;
        Amount maxPriceAmount = null;

        if(minPrice != null)
            minPriceAmount = new Amount(minPrice);

        if(maxPrice != null)
            maxPriceAmount = new Amount(maxPrice);

        return new ProductFilterRequest(sellerId, title, category, minPriceAmount, maxPriceAmount);
    }

}
