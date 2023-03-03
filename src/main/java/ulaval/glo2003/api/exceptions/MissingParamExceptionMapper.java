package ulaval.glo2003.api.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ulaval.glo2003.domain.exceptions.ErrorResponse;
import ulaval.glo2003.domain.exceptions.ExceptionCode;

public class MissingParamExceptionMapper implements ExceptionMapper<MissingParamException> {
    @Override
    public Response toResponse(MissingParamException e) {
        ErrorResponse response = new ErrorResponse(ExceptionCode.MISSING_PARAMETER, e.getMessage());
        return Response.status(400).entity(response).build();
    }
}
