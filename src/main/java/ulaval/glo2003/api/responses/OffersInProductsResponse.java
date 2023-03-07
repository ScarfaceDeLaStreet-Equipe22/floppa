package ulaval.glo2003.api.responses;

public class OffersInProductsResponse {
    public Integer count;
    public double avgAmount;

    public OffersInProductsResponse(int numberOfOffers, Double average) {
        this.count = numberOfOffers;
        this.avgAmount = average;
    }
}
