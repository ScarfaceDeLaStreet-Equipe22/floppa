package ulaval.glo2003.domain.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.domain.exceptions.OfferExceptions.NotPermittedException;

public class NotPermitedExceptionMapper implements ExceptionMapper<NotPermittedException> {
    public Response toResponse(NotPermittedException e) {
        ErrorResponse response = new ErrorResponse(ExceptionCode.NOT_PERMITTED, e.getMessage());
        return Response.status(404).entity(response).build();
    }
}
