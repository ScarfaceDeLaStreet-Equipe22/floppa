package ulaval.glo2003.api.mappers;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.SellerResponse;
import ulaval.glo2003.domain.entities.Seller;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SellerMapperTest {

    @Test
    public void mapEntityToResponse_shouldMapSellerToSellerResponse() {
        // Arrange

        Seller seller = new Seller("John Doe", "1968-05-12", "johndoe@example.com", "14185691931", "Bio");


        SellerMapper sellerMapper = new SellerMapper();

        // Act
        SellerResponse sellerResponse = sellerMapper.mapEntityToResponse(seller);

        // Assert
        assertEquals(seller.getId(), sellerResponse.getId());
        assertEquals(seller.getName(), sellerResponse.getName());
        assertEquals(seller.getBio(), sellerResponse.getBio());
        assertEquals(seller.getBirthdate().getDate(), sellerResponse.getBirthdate());
        assertEquals(seller.getEmail(), sellerResponse.getEmail());
        assertEquals(seller.getPhoneNumber(), sellerResponse.getPhoneNumber());
        assertEquals(seller.getProducts(), sellerResponse.getProducts());
    }

    @Test
    public void mapRequestToEntity_shouldMapSellerRequestToSeller() {
        // Arrange
        SellerRequest sellerRequest = new SellerRequest("John Doe", "BIO", "1968-05-12", "johndoe@example.com","14185691931");
        SellerMapper sellerMapper = new SellerMapper();

        // Act
        Seller seller = sellerMapper.mapRequestToEntity(sellerRequest);

        // Assert
        assertEquals(sellerRequest.getName(), seller.getName());
        assertEquals(sellerRequest.getBirthdate(), seller.getBirthdate().getDate());
        assertEquals(sellerRequest.getEmail(), seller.getEmail());
        assertEquals(sellerRequest.getPhoneNumber(), seller.getPhoneNumber());
        assertEquals(sellerRequest.getBio(), seller.getBio());
    }


}
