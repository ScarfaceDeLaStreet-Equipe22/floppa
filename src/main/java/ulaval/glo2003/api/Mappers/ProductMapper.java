package ulaval.glo2003.api.Mappers;

import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Product.ProductResponse;
import ulaval.glo2003.domain.Product;
import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;
import ulaval.glo2003.domain.Seller;

public class ProductMapper{


    public ProductMapper(){}
    public ProductResponse mapEntityToResponse(Product product) {
        return new ProductResponse(
                product.getTitle(),
                product.getDescription(),
                product.getCategory(),
                product.getSuggestedPrice(),
                product.getId(),
                product.getCreatedAt(),
                product.getSeller(),
                product.getNumberOfOffers(),
                product.getAverageAmountOfOffers());
    }

    public Product mapRequestToEntity(ProductRequest productRequest, Seller seller) {
        return new Product(
                productRequest.getTitle(),
                productRequest.getDescription(),
                new ProductCategory(productRequest.getCategory()),
                new Amount(productRequest.getSuggestedPrice()),
                seller);
    }
}
