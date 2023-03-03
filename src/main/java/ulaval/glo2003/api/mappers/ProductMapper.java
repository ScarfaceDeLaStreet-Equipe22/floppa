package ulaval.glo2003.api.mappers;

import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.api.responses.ProductResponse;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;
import ulaval.glo2003.domain.validators.ProductValidator;

public class ProductMapper {

    public ProductMapper() {}

    public ProductResponse mapEntityToResponse(Product product) {
        return new ProductResponse(
                product.getTitle(),
                product.getDescription(),
                product.getCategory(),
                product.getSuggestedPrice().toDouble(),
                product.getId(),
                product.getCreatedAt(),
                product.getSeller(),
                product.getNumberOfOffers(),
                product.getAverageAmountOfOffers());
    }

    public Product mapRequestToEntity(ProductRequest productRequest, Seller seller) {
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