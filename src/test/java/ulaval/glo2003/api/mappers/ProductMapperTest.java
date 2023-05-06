package ulaval.glo2003.api.mappers;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.api.responses.ProductResponse;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    public void setUpMapper() {
        productMapper = new ulaval.glo2003.api.mappers.ProductMapper();
    }

    @Test
    public void mapRequestToEntity_withValidRequest_shouldReturnProduct() {
        ProductRequest request = new ProductRequest("title", "description", "Sport", "10.0");
        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
        SellerMongoModel sellerMongoModel = new SellerMongoModel(seller);

        Product product = productMapper.mapRequestToEntity(request, sellerMongoModel);
        product.seller = seller;

        assertThat(product).isNotNull();
        assertThat(product.getTitle()).isEqualTo(request.getTitle());
        assertThat(product.getDescription()).isEqualTo(request.getDescription());
        assertThat(product.getCategory()).isEqualTo(request.getCategory());
        assertThat(product.getSuggestedPrice().toDouble())
                .isEqualTo(new Amount(request.getSuggestedPrice()).toDouble());
        assertThat(product.getSeller()).isEqualTo(seller);
    }

    @Test
    public void mapEntityToResponse_withValidProduct_shouldReturnProductResponse() {

        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
        SellerMongoModel sellerMongoModel = new SellerMongoModel(seller);
        Product product =
                new Product(
                        "title",
                        "description",
                        new ProductCategory("Sport"),
                        new Amount("10"),
                        sellerMongoModel);
        product.seller = seller;

        ProductResponse response = productMapper.mapEntityToResponse(product);

        assertThat(product).isNotNull();
        assertThat(product.getTitle()).isEqualTo(response.title);
        assertThat(product.getDescription()).isEqualTo(response.description);
        assertThat(product.getCategory()).isEqualTo(response.category);
        assertThat(product.getSuggestedPrice().toDouble()).isEqualTo(response.suggestedPrice);
    }
}
