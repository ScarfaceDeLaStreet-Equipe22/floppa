package ulaval.glo2003;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class IllegalParameterException extends Throwable implements ExceptionMapper<IllegalArgumentException> {


//    @Override
//    public Response toResponse(IllegalParameterException e) {
//        exception.printStackTrace();
//        return Response.status(400).entity("argument Illegal").build();
//    }

    @Override
    public Response toResponse(IllegalArgumentException e) {
        return Response.status(400).entity("argument Illegal").build();
    }
}
