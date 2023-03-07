package ulaval.glo2003.api.responses;

import java.util.ArrayList;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.utils.DateTime;

public class SellerResponse {
    public String name;
    public String bio;
    public String birthdate;
    public String email;
    public String phoneNumber;

    public String createdAt;
    public ArrayList<ProductResponse> products;
    public String id;

    public SellerResponse(
            String id,
            String name,
            String bio,
            String birthDate,
            String email,
            String phoneNumber,
            ArrayList<ProductResponse> products,
            DateTime createdAt) {
        this.id = id;
        this.name = name;
        this.birthdate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.products = products;
        this.createdAt = createdAt.getDateTime();
    }

    /*
    private ArrayList<ProductResponse> transformProductIntoProductResponse(
            ArrayList<Product> productList) {
        ArrayList<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : productList) {
            ProductResponse productResponse =
                    new ProductResponse(
                            product.getTitle(),
                            product.getDescription(),
                            product.getCategory(),
                            product.getSuggestedPrice().toDouble(),
                            product.getId(),
                            product.getCreatedAt(),
                            product.getOffers(),
                            product.getNumberOfOffers(),
                            product.getAverageAmountOfOffers(),
                            product.getMinimumAmountOfOffers(),
                            product.getMaximumAmountOfOffers());
            productResponses.add(productResponse);
        }

        return productResponses;
    }*/
}
