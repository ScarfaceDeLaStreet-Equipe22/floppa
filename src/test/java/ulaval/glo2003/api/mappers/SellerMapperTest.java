package ulaval.glo2003.api.mappers;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.ProductResponse;
import ulaval.glo2003.api.responses.SellerResponse;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

class SellerMapperTest {
    private SellerMapper sellerMapper;

    @BeforeEach
    public void setUpMapper() {
        sellerMapper = new ulaval.glo2003.api.mappers.SellerMapper();
    }

    @Test
    public void mapRequestToEntity_withValidRequest_shouldReturnSeller() {
        // arrange
        SellerRequest request = new SellerRequest("name", "01-01-1995", "test@test.com", "18191234567", "bio");

        // act
        Seller seller = sellerMapper.mapRequestToEntity(request);

        // assert
        assertThat(seller).isNotNull();
        assertThat(seller.getName()).isEqualTo(request.getName());
        assertThat(seller.getBio()).isEqualTo(request.getBio());
        assertThat(seller.getBirthdate()).isEqualTo(request.getBirthdate());
        assertThat(seller.getEmail()).isEqualTo(request.getEmail());
        assertThat(seller.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
    }

    public void mapEntityToResponse_withValidSeller_shouldReturnSellerResponse() {
        // Given
        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");

        // When
        SellerResponse response = sellerMapper.mapEntityToResponse(seller);

        // assert
        assertThat(seller).isNotNull();
        assertThat(seller.getName()).isEqualTo(response.name);
        assertThat(seller.getBio()).isEqualTo(response.bio);
        assertThat(seller.getBirthdate()).isEqualTo(response.birthDate);
        assertThat(seller.getEmail()).isEqualTo(response.email);
        assertThat(seller.getPhoneNumber()).isEqualTo(response.phoneNumber);
    }
}
