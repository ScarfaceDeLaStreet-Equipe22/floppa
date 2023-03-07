package ulaval.glo2003.api.mappers;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.SellerResponse;
import ulaval.glo2003.domain.entities.Seller;

class SellerMapperTest {
    private SellerMapper sellerMapper;

    @BeforeEach
    public void setUpMapper() {
        sellerMapper = new ulaval.glo2003.api.mappers.SellerMapper();
    }

    @Test
    public void mapRequestToEntity_withValidRequest_shouldReturnSeller() {
        // arrange
        SellerRequest request = new SellerRequest("name", "allo", "1965-01-01", "test123@gmail.com", "18191234567");

        // act
        Seller seller = sellerMapper.mapRequestToEntity(request);

        // assert
        assertThat(seller).isNotNull();
        assertThat(seller.getName()).isEqualTo(request.getName());
        assertThat(seller.getBio()).isEqualTo(request.getBio());
        assertThat(seller.getBirthdate().getDate()).isEqualTo(request.getBirthdate());
        assertThat(seller.getEmail()).isEqualTo(request.getEmail());
        assertThat(seller.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
    }

    public void mapEntityToResponse_withValidSeller_shouldReturnSellerResponse() {
        // Given
        Seller seller = new Seller("name", "1965-01-01", "test@test.com", "18191234567", "bio");

        // When
        SellerResponse response = sellerMapper.mapEntityToResponse(seller);

        // assert
        assertThat(seller).isNotNull();
        assertThat(seller.getName()).isEqualTo(response.name);
        assertThat(seller.getBio()).isEqualTo(response.bio);
        assertThat(seller.getBirthdate().getDate()).isEqualTo(response.birthdate);
        assertThat(seller.getEmail()).isEqualTo(response.email);
        assertThat(seller.getPhoneNumber()).isEqualTo(response.phoneNumber);
    }
}
