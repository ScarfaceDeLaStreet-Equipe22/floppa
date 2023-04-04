package ulaval.glo2003.api.mappers;

import java.util.ArrayList;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.OfferResponse;
import ulaval.glo2003.api.responses.OffersInProductsInSellerResponse;
import ulaval.glo2003.api.responses.ProductResponse;
import ulaval.glo2003.api.responses.SellerResponse;
import ulaval.glo2003.api.validators.SellerRequestValidator;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.validators.SellerValidator;

public class SellerMapper {

    private final OfferMapper offerMapper = new OfferMapper();

    public SellerResponse mapEntityToResponse(Seller seller) {
        SellerValidator sellerValidator = new SellerValidator(seller);
        sellerValidator.validateEntity();

        ArrayList<ProductResponse> products = new ArrayList<>();

        for (Product product : seller.getProducts()) {
            OffersInProductsInSellerResponse offers = getOffersReponses(product);

            ProductResponse productReponse =
                    new ProductResponse(
                            product.getTitle(),
                            product.getDescription(),
                            product.getCategory(),
                            product.getSuggestedPrice().toDouble(),
                            product.getId(),
                            product.getCreatedAt(),
                            offers);

            products.add(productReponse);
        }

        return new SellerResponse(
                seller.getId(),
                seller.getName(),
                seller.getBio(),
                seller.getBirthdate().getDate(),
                seller.getEmail(),
                seller.getPhoneNumber(),
                products,
                seller.getCreatedAt());
    }

    private OffersInProductsInSellerResponse getOffersReponses(Product product) {
        ArrayList<OfferResponse> offerResponses = new ArrayList<OfferResponse>();

        OffersInProductsInSellerResponse offersInProductsInSellerResponse = new OffersInProductsInSellerResponse(
                product.getNumberOfOffers(),
                product.getAverageAmountOfOffers(),
                product.getMinimumAmountOfOffers(),
                product.getMaximumAmountOfOffers(),
                offerResponses);
        if (product.getOffers() != null) {
            for (Offer offer : product.getOffers()){
                offersInProductsInSellerResponse.items.add(offerMapper.mapEntityToResponse(offer));
            }
            return offersInProductsInSellerResponse;
        } else {
            return offersInProductsInSellerResponse;
        }
    }

    public Seller mapRequestToEntity(SellerRequest sellerRequest) {
        SellerRequestValidator sellerRequestValidator = new SellerRequestValidator(sellerRequest);
        sellerRequestValidator.validateRequest();

        Seller seller =
                new Seller(
                        sellerRequest.getName(),
                        sellerRequest.getBirthdate(),
                        sellerRequest.getEmail(),
                        sellerRequest.getPhoneNumber(),
                        sellerRequest.getBio());
        SellerValidator sellerValidator = new SellerValidator(seller);
        sellerValidator.validateEntity();
        return seller;
    }
}
