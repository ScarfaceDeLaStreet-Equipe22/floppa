package ulaval.glo2003;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class MissingParameterException extends Throwable implements ExceptionMapper<RuntimeException> {


    @Override
    public Response toResponse(RuntimeException exception) {
        exception.printStackTrace();
        return Response.status(400).entity("tu as fait une erreur").build();
    }

}
