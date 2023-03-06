package ulaval.glo2003.api.responses;

import ulaval.glo2003.domain.utils.DateTime;

public class OfferResponse {
    public String username;
    public String createdAt;
    public Double amount;
    public String message;

    public OfferResponse(String buyerUsername, DateTime createdAt, Double amount, String message) {
        this.username = buyerUsername;
        this.createdAt = createdAt.getDateTime();
        this.amount = amount;
        this.message = message;
    }
}
