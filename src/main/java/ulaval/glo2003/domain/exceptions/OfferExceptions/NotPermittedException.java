package ulaval.glo2003.domain.exceptions.OfferExceptions;

public class NotPermittedException extends RuntimeException {
    public NotPermittedException() {
        super("An offer on this product already exist");
    }
}
