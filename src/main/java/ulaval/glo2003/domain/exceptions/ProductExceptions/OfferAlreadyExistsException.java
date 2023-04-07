package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.OfferExceptions.NotPermittedException;

public class OfferAlreadyExistsException extends NotPermittedException {
    public OfferAlreadyExistsException(){super("Offer already exists");}
}
