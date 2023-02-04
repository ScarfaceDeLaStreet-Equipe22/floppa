package ulaval.glo2003.api.ProductExceptions;


public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException() {
        super("Id of the Seller not found");
    }
}
