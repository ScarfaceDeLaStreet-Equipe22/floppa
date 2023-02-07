package ulaval.glo2003.api.Utils;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;

public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

    public Response toResponse(ItemNotFoundException e) {
        ErrorResponse response = new ErrorResponse(ExceptionCode.ITEM_NOT_FOUND, e.getMessage());
        return Response.status(404).entity(response).build();
    }
}
