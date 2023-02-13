package ulaval.glo2003.domain;
import ulaval.glo2003.domain.ProductClasses.*;

public class Offer {

    public Amount amount;
    public String message ;

    public Offer(String amount, String message) {
        this.amount =  new Amount(amount);
        this.message = message;
    }

    public Double getAmount() {
        return amount.getAmount();
    }

    public String getMessage() {
        return message;
    }
}
