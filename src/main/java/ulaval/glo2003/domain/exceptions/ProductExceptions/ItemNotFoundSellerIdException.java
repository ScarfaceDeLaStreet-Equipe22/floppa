package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.ItemNotFoundException;

public class ItemNotFoundSellerIdException extends ItemNotFoundException {
    public ItemNotFoundSellerIdException() {
        super("Id of the Seller not found");
    }
}
