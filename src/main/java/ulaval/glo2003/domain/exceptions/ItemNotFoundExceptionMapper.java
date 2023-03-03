package ulaval.glo2003.domain.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

    public Response toResponse(ItemNotFoundException e) {
        ErrorResponse response = new ErrorResponse(ExceptionCode.ITEM_NOT_FOUND, e.getMessage());
        return Response.status(404).entity(response).build();
    }
}
