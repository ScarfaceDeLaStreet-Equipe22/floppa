package ulaval.glo2003.api.mappers;

import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidFilterMaxPriceException;
import ulaval.glo2003.domain.exceptions.ProductExceptions.InvalidFilterMinPriceException;
import ulaval.glo2003.domain.utils.ProductFilters;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

public class ProductFiltersMapper {

    public ProductFilters mapQueryParamsToRequest(String sellerId, String title, String categoryName, String minPrice, String maxPrice)
    {
        ProductCategory category = new ProductCategory(categoryName);
        Amount minPriceAmount = null;
        Amount maxPriceAmount = null;

        try
        {
            if(minPrice != null)
                minPriceAmount = new Amount(minPrice);
        }catch (InvalidAmountException ex)
        {
            throw new InvalidFilterMinPriceException();
        }


        try
        {
            if(maxPrice != null)
                maxPriceAmount = new Amount(maxPrice);
        }catch (InvalidAmountException ex)
        {
            throw new InvalidFilterMaxPriceException();
        }


        return new ProductFilters(sellerId, title, category, minPriceAmount, maxPriceAmount);
    }

}
