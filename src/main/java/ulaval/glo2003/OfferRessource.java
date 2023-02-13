package ulaval.glo2003;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import ulaval.glo2003.api.Offer.OfferRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.api.ProductExceptions.ItemNotFoundException;
import ulaval.glo2003.domain.* ;

import java.util.ArrayList;

@Path("/offers")
public class OfferRessource {

    public ArrayList<Product> allProducts ;

    public OfferRessource(ArrayList<Product> allProducts) {
        this.allProducts = allProducts ;
    }



    @GET
    @Path("{Productid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response offre(OfferRequest request, @PathParam("Productid") String productId){



        Offer offer = new Offer(request.amount, request.message);

        Product productNeeded = getProduct(productId) ;

        productNeeded.addOffer(offer);

        return Response.status(201).entity("offre ajoutée").build() ;
    }


    public Product getProduct(String id){
        Product productNeeded = null;
        for (Product product : allProducts){
            if (product.getId().equals(id)){
                productNeeded = product ;
            }
            else {
                throw new ItemNotFoundException() ;
            }
        }
        return productNeeded ;
    }
}
