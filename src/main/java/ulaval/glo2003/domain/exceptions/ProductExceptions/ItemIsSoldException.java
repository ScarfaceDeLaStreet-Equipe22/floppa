package ulaval.glo2003.domain.exceptions.ProductExceptions;


import ulaval.glo2003.domain.exceptions.OfferExceptions.NotPermittedException;

public class ItemIsSoldException extends NotPermittedException {
    public ItemIsSoldException(){super("Product already sold");}
}
