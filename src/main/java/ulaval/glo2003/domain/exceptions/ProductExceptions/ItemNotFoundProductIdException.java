package ulaval.glo2003.domain.exceptions.ProductExceptions;

import ulaval.glo2003.domain.exceptions.ItemNotFoundException;

public class ItemNotFoundProductIdException extends ItemNotFoundException {

    public ItemNotFoundProductIdException() {
        super("Id of the Product not found");
    }
}
