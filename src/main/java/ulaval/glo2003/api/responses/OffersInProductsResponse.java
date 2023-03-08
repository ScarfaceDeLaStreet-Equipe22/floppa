package ulaval.glo2003.api.responses;

import java.util.Optional;

public class OffersInProductsResponse {

    public Integer count;
    public Optional<Double> avgAmount = Optional.empty();

    public OffersInProductsResponse(int numberOfOffers, Double average) {
        this.count = numberOfOffers;

        if (count > 0) avgAmount = Optional.of(average);
    }
}
