package ulaval.glo2003.api.Utils;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class InvalidParamExceptionMapper implements ExceptionMapper<InvalidParamException> {
    @Override
    public Response toResponse(InvalidParamException e) {
        ErrorResponse response = new ErrorResponse(
                ExceptionCode.INVALID_PARAMETER,
                e.getMessage()
        );
        return Response.status(400).entity(response).build();
    }
}

