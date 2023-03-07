package ulaval.glo2003.api.responses;

import java.util.ArrayList;
import java.util.Optional;

public class OffersInProductsInSellerResponse extends OffersInProductsResponse {

    public Optional<Double> minAmount = Optional.empty();
    public Optional<Double> maxAmount = Optional.empty();
    public ArrayList<OfferResponse> items;

    public OffersInProductsInSellerResponse(
            int numberOfOffers,
            Double average,
            double minAmount,
            double maxAmount,
            ArrayList<OfferResponse> items) {
        super(numberOfOffers, average);

        if (numberOfOffers > 0) {
            this.minAmount = Optional.of(minAmount);
            this.maxAmount = Optional.of(maxAmount);
        }

        this.items = items;
    }
}
