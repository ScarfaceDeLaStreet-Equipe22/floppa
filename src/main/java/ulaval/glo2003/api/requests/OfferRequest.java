package ulaval.glo2003.api.requests;

public class OfferRequest {

    public String amount;
    public String message;

    public OfferRequest() {}
    public OfferRequest(String amount, String Message){
        this.amount = amount;
        this.message = Message;
    }

    public String getAmount() {

        return amount;
    }

    public String getMessage() {

        return message;
    }
}
