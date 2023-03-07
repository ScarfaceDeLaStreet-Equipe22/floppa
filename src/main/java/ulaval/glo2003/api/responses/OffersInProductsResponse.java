package ulaval.glo2003.api.responses;

import java.util.Optional;

public class OffersInProductsResponse {
    public Integer count;
    public Optional<Double> avgAmount = Optional.empty();

    public OffersInProductsResponse(int numberOfOffers, Double average) {
        this.count = numberOfOffers;

        if (count > 0) avgAmount = Optional.of(average);
    }

    /*  public OffersInProductsResponse(ArrayList offers, int numberOfOffers, Double average, double minimumAmount, double maximumAmount) {
        if (numberOfOffers == 0) {
            this.count = numberOfOffers;
            this.items = new ArrayList<>();
        } else {
            this.count = numberOfOffers;
            this.avgAmount = average;
            this.minimumAmount = minimumAmount;
            this.maximumAmount = maximumAmount;
            this.items = transformOfferIntoOfferResponse(offers);
        }
    }
    private ArrayList<OfferResponse> transformOfferIntoOfferResponse(
            ArrayList<Offer> offersList) {
        ArrayList<OfferResponse> OfferResponses = new ArrayList<>();
        for (Offer offer : offersList) {
            OfferResponse offerResponse =
                    new OfferResponse(
                            offer.getBuyerUsername(),
                            offer.getCreatedAt(),
                            offer.getAmount(),
                            offer.getMessage());
            OfferResponses.add(offerResponse);
        }

        return OfferResponses;
    }*/
}
