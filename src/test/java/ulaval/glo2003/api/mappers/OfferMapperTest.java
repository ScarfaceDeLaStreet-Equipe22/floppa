package ulaval.glo2003.api.mappers;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingAmountException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingMessageException;
import ulaval.glo2003.api.requests.OfferRequest;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidMessageException;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

class OfferMapperTest {

    private OfferMapper offerMapper;
    private OfferRequest offerRequest;
    private Product product;
    private static final String MESSAGE = "a".repeat(100);
    private static final String AMOUNT = "20";
    private static final String BUYERUSERNAME = "buyerUsername";
    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;

    @BeforeEach
    public void setUp() {
        offerMapper = new OfferMapper();
        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
        SellerMongoModel sellerMongoModel = new SellerMongoModel(seller);
        product =
                new Product(
                        "product title",
                        "product description",
                        new ProductCategory("Sport"),
                        new Amount("15"),sellerMongoModel
                        );
    }

    @Test
    public void givenMissingAmount_whenMappingRequestToEntity_thenThrowsMissingAmountException() {
        offerRequest = new OfferRequest(NULL_STRING, MESSAGE);

        Executable executable = () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product);

        assertThrows(MissingAmountException.class, executable);
    }

    @Test
    public void givenMissingMessage_whenMappingRequestToEntity_thenThrowsMissingMessageException() {
        offerRequest = new OfferRequest(AMOUNT, NULL_STRING);

        Executable executable = () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product);

        assertThrows(MissingMessageException.class, executable);
    }

    @Test
    public void
            givenAmountLessThanSuggestedprice_whenMappingRequestToEntity_thenThrowsInvalidAmountException() {
        offerRequest = new OfferRequest("2", MESSAGE);

        Executable executable = () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product);

        assertThrows(InvalidAmountException.class, executable);
    }

    @Test
    public void givenInvalidAmount_whenMappingRequestToEntity_thenThrowsInvalidAmountException() {
        offerRequest = new OfferRequest("a", MESSAGE);

        Executable executable = () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product);

        assertThrows(InvalidAmountException.class, executable);
    }

    @Test
    public void givenInvalidMessage_whenMappingRequestToEntity_thenThrowsInvalidMessageException() {
        offerRequest = new OfferRequest(AMOUNT, EMPTY_STRING);

        Executable executable = () -> offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product);

        assertThrows(InvalidMessageException.class, executable);
    }

    @Test
    public void givenValidRequest_whenMappingRequestToEntity_thenReturnsValidOffer() {
        offerRequest = new OfferRequest(AMOUNT, MESSAGE);

        Offer result = offerMapper.mapRequestToEntity(offerRequest, BUYERUSERNAME, product);

        assertThat(result.getAmount()).isEqualTo(new Amount(AMOUNT).toDouble());
        assertThat(result.getMessage()).isEqualTo(MESSAGE);
        assertThat(result.getBuyerUsername()).isEqualTo(BUYERUSERNAME);
    }
}
