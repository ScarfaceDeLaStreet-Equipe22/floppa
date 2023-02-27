package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.ItemNotFoundException;

public class ItemNotFoundSellerIdException extends ItemNotFoundException {
    public ItemNotFoundSellerIdException() {
        super("Id of the Seller not found");
    }
}
