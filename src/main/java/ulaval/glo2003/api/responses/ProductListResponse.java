package ulaval.glo2003.api.responses;

import java.util.List;

public class ProductListResponse {
    public List<ProductResponse> products;

    public ProductListResponse(List<ProductResponse> products) {
        this.products = products;
    }
}
