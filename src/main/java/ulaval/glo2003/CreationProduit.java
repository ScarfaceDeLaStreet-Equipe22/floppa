package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.geom.Arc2D;
import java.net.MalformedURLException;
import java.net.URL;

@Path("/products")
public class CreationProduit {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pong(ProductRequest request, @HeaderParam("X-Seller-Id") String SellerId) throws IllegalParameterException, MissingParameterException{

        Product product ;

        if (request.title.isEmpty() || request.category.isEmpty()  || request.description.isEmpty() ){
            throw new MissingParameterException() ;
        } else {
            product = new Product(request.title, request.description, request.category, request.suggestedPrice);
            return Response.status(201).entity("url").build();
        }


    }



}
