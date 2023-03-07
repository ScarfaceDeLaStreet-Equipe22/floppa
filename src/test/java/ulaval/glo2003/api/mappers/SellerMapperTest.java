package ulaval.glo2003.api.mappers;

import static org.junit.jupiter.api.Assertions.*;

class SellerMapperTest {

    @Test
    public void mapEntityToResponse_shouldMapSellerToSellerResponse() {
        // Arrange
        LocalDate birthdate = LocalDate.now().minusYears(30);
        Seller seller = new Seller("John Doe", birthdate, "johndoe@example.com", "123-456-7890", "Bio");
        seller.setId("123");
        seller.setCreatedAt(LocalDate.now().minusDays(7));

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
        assertEquals(seller.getCreatedAt(), sellerResponse.getCreatedAt());
    }

    @Test
    public void mapRequestToEntity_shouldMapSellerRequestToSeller() {
        // Arrange
        SellerRequest sellerRequest = new SellerRequest("John Doe", LocalDate.now().minusYears(30), "johndoe@example.com", "123-456-7890", "Bio");
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