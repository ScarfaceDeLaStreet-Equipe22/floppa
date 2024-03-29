package ulaval.glo2003.domain.validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.entities.SellerMongoModel;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidMessageException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.NotPermittedException;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

public class OfferValidatorTest {

    private Product product;
    private static final Amount LOW_AMOUNT = new Amount("10");
    private static final Amount MEDIUM_AMOUNT = new Amount("15");
    private static final Amount HIGH_AMOUNT = new Amount("25");
    private static final String message = "a".repeat(100);

    @BeforeEach
    public void setUp() {
        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
        SellerMongoModel sellerMongoModel = new SellerMongoModel(seller);
        product =
                new Product(
                        "product title",
                        "product description",
                        new ProductCategory("Sport"),
                        MEDIUM_AMOUNT,
                        sellerMongoModel);
    }

    @Test
    public void givenEmptyOfferAmount_whenValidatingEntity_thenThrowsInvalidAmountException() {
        Offer offerWithInvalidAmount = new Offer(new Amount("0"), message, "buyerUsername");
        OfferValidator offerValidator = new OfferValidator(offerWithInvalidAmount, product);

        Executable executable = offerValidator::validateEntity;

        assertThrows(InvalidAmountException.class, executable, "Invalid parameter 'Amount'.");
    }

    @Test
    public void
            givenOfferMessageLengthLessThan100_whenValidatingEntity_thenThrowsInvalidDescriptionException() {
        Offer offerWithInvalidMessage =
                new Offer(HIGH_AMOUNT, "message less than 100 char", "buyerUsername");
        OfferValidator offerValidator = new OfferValidator(offerWithInvalidMessage, product);

        Executable executable = offerValidator::validateEntity;

        assertThrows(InvalidMessageException.class, executable, "Invalid parameter 'message'.");
    }

    @Test
    public void
            givenOfferAmountLessThanSuggestedPrice_whenValidatingEntity_thenThrowsInvalidAmountException() {
        Offer offerWithInvalidMessage = new Offer(LOW_AMOUNT, message, "buyerUsername");
        OfferValidator offerValidator = new OfferValidator(offerWithInvalidMessage, product);

        Executable executable = offerValidator::validateEntity;

        assertThrows(InvalidAmountException.class, executable, "Invalid parameter 'Amount'.");
    }

    @Test
    public void
            givenBuyerUsernameAlreadyMadeOffer_whenValidatingEntity_thenThrowsNotPermittedException() {
        Offer validOffer = new Offer(HIGH_AMOUNT, message, "buyerUsername");
        product.offers.add(validOffer);
        Offer sameBuyerOffer = new Offer(HIGH_AMOUNT, message, "buyerUsername");
        OfferValidator offerValidator = new OfferValidator(sameBuyerOffer, product);

        Executable executable = offerValidator::validateEntity;

        assertThrows(
                NotPermittedException.class, executable, "An offer on this product already exist");
    }

    @Test
    public void givenValidOfferAndProduct_whenValidatingEntity_thenDoesNotThrowException() {
        Offer validOffer = new Offer(HIGH_AMOUNT, message, "buyerUsername");
        OfferValidator offerValidator = new OfferValidator(validOffer, product);

        Executable executable = offerValidator::validateEntity;

        assertDoesNotThrow(executable);
    }

    @Test
    public void
            givenValidOfferAndProductWithSameAmount_whenValidatingEntity_thenDoesNotThrowException() {
        Offer validOffer = new Offer(MEDIUM_AMOUNT, message, "buyerUsername");
        OfferValidator offerValidator = new OfferValidator(validOffer, product);

        Executable executable = offerValidator::validateEntity;

        assertDoesNotThrow(executable);
    }
}
