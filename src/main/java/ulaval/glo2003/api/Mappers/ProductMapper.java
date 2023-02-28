package ulaval.glo2003.api.Mappers;

import ulaval.glo2003.api.Product.ProductRequest;
import ulaval.glo2003.api.Product.ProductResponse;
import ulaval.glo2003.domain.Product;

public class ProductMapper implements IMapper<Product, ProductResponse, ProductRequest> {

    public ProductResponse mapEntitytoResponse(Product product) {
        return null;
    }

    public Product mapResponsetoEntity(ProductRequest productRequest) {
        return null;
    }
}
