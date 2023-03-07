package ulaval.glo2003.api.mappers;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingAmountException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingMessageException;
import ulaval.glo2003.api.requests.OfferRequest;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidMessageException;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;
import ulaval.glo2003.api.mappers.OfferMapper ;


class OfferMapperTest {
    private ulaval.glo2003.api.mappers.OfferMapper offerMapper;
    private OfferRequest offerRequest;
    private Product product;
    private static final String MESSAGE = "a".repeat(100);
    private static final String AMOUNT = "20";
    private static final String BUYERUSERNAME = "buyerUsername";
    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;

    @BeforeEach
    public void setUp() {
        offerMapper = new ulaval.glo2003.api.mappers.OfferMapper();
        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
        product =
                new Product(
                        "product title",
                        "product description",
                        new ProductCategory("Sport"),
                        new Amount("15"),
                        seller);
    }

    @Test
    public void givenMissingAmount_whenMappingRequestToEntity_thenThrowsMissingAmountException() {
        // arrange
        offerRequest = new OfferRequest(NULL_STRING, MESSAGE);

        // act & assert
        assertThrows(
                MissingAmountException.class,
                () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product));
    }

    @Test
    public void givenMissingMessage_whenMappingRequestToEntity_thenThrowsMissingMessageException() {
        // arrange
        offerRequest = new OfferRequest(AMOUNT, NULL_STRING);

        // act & assert
        assertThrows(
                MissingMessageException.class,
                () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product));
    }

    @Test
    public void
            givenAmountLessThanSuggestedprice_whenMappingRequestToEntity_thenThrowsInvalidAmountException() {
        // arrange
        offerRequest = new OfferRequest("2", MESSAGE);

        // act & assert
        assertThrows(
                InvalidAmountException.class,
                () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product));
    }

    @Test
    public void givenInvalidAmount_whenMappingRequestToEntity_thenThrowsInvalidAmountException() {
        // arrange
        offerRequest = new OfferRequest("a", MESSAGE);

        // act & assert
        assertThrows(
                InvalidAmountException.class,
                () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product));
    }

    @Test
    public void givenInvalidMessage_whenMappingRequestToEntity_thenThrowsInvalidMessageException() {
        // arrange
        offerRequest = new OfferRequest(AMOUNT, EMPTY_STRING);

        // act & assert
        assertThrows(
                InvalidMessageException.class,
                () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product));
    }

    @Test
    public void givenValidRequest_whenMappingRequestToEntity_thenReturnsValidOffer() {
        // arrange
        offerRequest = new OfferRequest(AMOUNT, MESSAGE);

        // act
        Offer result = offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product);

        // assert
        assertThat(result.getAmount()).isEqualTo(new Amount(AMOUNT).toDouble());
        assertThat(result.getMessage()).isEqualTo(MESSAGE);
        assertThat(result.getBuyerUsername()).isEqualTo(BUYERUSERNAME);
    }
}
