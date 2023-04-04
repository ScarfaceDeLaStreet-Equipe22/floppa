package ulaval.glo2003.api.mappers;

import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.api.responses.OffersInProductsResponse;
import ulaval.glo2003.api.responses.ProductResponse;
import ulaval.glo2003.api.validators.ProductRequestValidator;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;
import ulaval.glo2003.domain.validators.ProductValidator;

public class ProductMapper {

    public ProductMapper() {}

    public ProductResponse mapEntityToResponse(Product product) {
        ProductValidator productValidator = new ProductValidator(product);
        productValidator.validateEntity();

        OffersInProductsResponse offers =
                new OffersInProductsResponse(
                        product.getNumberOfOffers(), product.getAverageAmountOfOffers());

        return new ProductResponse(
                product.getTitle(),
                product.getDescription(),
                product.getCategory(),
                product.getSuggestedPrice().toDouble(),
                product.getId(),
                product.getCreatedAt(),
                product.getSeller(),
                offers);
    }

    public Product mapRequestToEntity(ProductRequest productRequest, SellerMongoModel seller) {
        ProductRequestValidator productRequestValidator =
                new ProductRequestValidator(productRequest);
        productRequestValidator.validateRequest();

        Product product =
                new Product(
                        productRequest.getTitle(),
                        productRequest.getDescription(),
                        new ProductCategory(productRequest.getCategory()),
                        new Amount(productRequest.getSuggestedPrice()),
                        seller);
        ProductValidator productValidator = new ProductValidator(product);
        productValidator.validateEntity();

        return product;
    }
}
