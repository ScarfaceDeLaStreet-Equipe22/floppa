package ulaval.glo2003.Utils;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class MissingParamExceptionMapper implements ExceptionMapper<MissingParamException> {
    @Override
    public Response toResponse(MissingParamException e) {
        ErrorResponse response = new ErrorResponse(
                ExceptionCode.MISSING_PARAMETER,
                e.getMessage()
        );
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response).build();
    }
}
