package ulaval.glo2003.Product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.IllegalParameterException;
import ulaval.glo2003.MissingParameterException;
import ulaval.glo2003.Product.Errors.ProductParameterValidator;

import java.awt.geom.Arc2D;

@Path("/products")
public class CreationProduit {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response pong(ProductRequest request, @HeaderParam("X-Seller-Id") String SellerId, @PathParam("id") String id) throws IllegalParameterException, MissingParameterException {

        Product product ;

        ProductCategory productCategory = new ProductCategory(request.category);

        ProductParameterValidator V = new ProductParameterValidator(request.title, request.description, productCategory, request.suggestedPrice);

        product = new Product(V.getTitle(), V.getDescription(), V.getCategory(), V.getSuggestedPrice());
        String url = "lol/" + SellerId ;
        return Response.status(201).entity(product).build();




    }



}
