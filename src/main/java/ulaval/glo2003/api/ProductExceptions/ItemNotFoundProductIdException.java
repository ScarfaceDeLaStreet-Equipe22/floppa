package ulaval.glo2003.api.ProductExceptions;

import ulaval.glo2003.api.Utils.ItemNotFoundException;

public class ItemNotFoundProductIdException extends ItemNotFoundException {

    public ItemNotFoundProductIdException() {
        super("Id of the Product not found");
    }
}
