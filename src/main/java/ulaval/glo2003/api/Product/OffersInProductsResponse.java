package ulaval.glo2003.api.Product;

import ulaval.glo2003.domain.ProductClasses.Amount;

public class OffersInProductsResponse {
    public Integer count ;
    public double avgAmount ;

    public OffersInProductsResponse(int numberOfOffers, Double average) {
        this.count = numberOfOffers;
        this.avgAmount = average ;
    }

    public Integer getCount() {
        return count;
    }

    public double getAvgAmount() {
        return avgAmount;
    }
}
