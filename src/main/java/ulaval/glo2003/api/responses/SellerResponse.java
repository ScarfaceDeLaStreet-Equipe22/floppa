package ulaval.glo2003.api.responses;

import java.util.ArrayList;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.utils.DateTime;

public class SellerResponse {
    public String name;
    public String bio;
    public String birthDate;
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
            ArrayList<Product> products,
            DateTime createdAt) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.products = transformProductIntoProductResponse(products);
        this.createdAt = createdAt.getDateTime();
    }

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
                            product.getNumberOfOffers(),
                            product.getAverageAmountOfOffers());
            productResponses.add(productResponse);
        }

        return productResponses;
    }
}
