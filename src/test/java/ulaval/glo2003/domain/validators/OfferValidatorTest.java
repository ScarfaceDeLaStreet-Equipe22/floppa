package ulaval.glo2003.domain.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.domain.entities.Offer;
import ulaval.glo2003.domain.entities.Product;
import ulaval.glo2003.domain.entities.Seller;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidAmountException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.InvalidMessageException;
import ulaval.glo2003.domain.exceptions.OfferExceptions.NotPermittedException;
import ulaval.glo2003.domain.utils.Amount;
import ulaval.glo2003.domain.utils.ProductCategory;

import static org.junit.jupiter.api.Assertions.*;
public class OfferValidatorTest {

    private Product product;
    private static final Amount LOW_AMOUNT = new Amount("10");
    private static final Amount MEDIUM_AMOUNT = new Amount("15");
    private static final Amount HIGH_AMOUNT = new Amount("25");
    private static final String message = "a".repeat(100);


    @BeforeEach
    public void setUp() {
        Seller seller = new Seller("name", "01-01-1995", "test@test.com", "18191234567", "bio");
        product = new Product("product title", "product description", new ProductCategory("Sport"), MEDIUM_AMOUNT, seller);
    }

    @Test
    public void givenEmptyOfferAmount_whenValidatingEntity_thenThrowsInvalidAmountException() {
        //arrange
        Offer offerWithInvalidAmount = new Offer(new Amount("0"), message,"buyerUsername");
        OfferValidator offerValidator = new OfferValidator(offerWithInvalidAmount, product);

        //act
        Executable executable = offerValidator::validateEntity;

        //assert
        assertThrows(InvalidAmountException.class, executable, "Invalid parameter 'Amount'.");
    }

    @Test
    public void givenOfferMessageLengthLessThan100_whenValidatingEntity_thenThrowsInvalidDescriptionException() {
        //arrange
        Offer offerWithInvalidMessage = new Offer(HIGH_AMOUNT, "message less than 100 char","buyerUsername");
        OfferValidator offerValidator = new OfferValidator(offerWithInvalidMessage, product);

        //act
        Executable executable = offerValidator::validateEntity;

        //assert
        assertThrows(InvalidMessageException.class, executable, "Invalid parameter 'message'.");
    }

    @Test
    public void givenOfferAmountLessThanSuggestedPrice_whenValidatingEntity_thenThrowsInvalidAmountException() {
        //arrange
        Offer offerWithInvalidMessage = new Offer(LOW_AMOUNT, message,"buyerUsername");
        OfferValidator offerValidator = new OfferValidator(offerWithInvalidMessage, product);

        //act
        Executable executable = offerValidator::validateEntity;

        //assert
        assertThrows(InvalidAmountException.class, executable, "Invalid parameter 'Amount'.");
    }

    @Test
    public void givenBuyerUsernameAlreadyMadeOffer_whenValidatingEntity_thenThrowsNotPermittedException() {
        //arrange
        Offer validOffer = new Offer(HIGH_AMOUNT, message,"buyerUsername");
        product.offers.add(validOffer);
        Offer sameBuyerOffer = new Offer(HIGH_AMOUNT, message,"buyerUsername");
        OfferValidator offerValidator = new OfferValidator(sameBuyerOffer, product);

        //act
        Executable executable = offerValidator::validateEntity;

        //assert
        assertThrows(NotPermittedException.class, executable, "An offer on this product already exist");
    }

    @Test
    public void givenValidOfferAndProduct_whenValidatingEntity_thenDoesNotThrowException() {
        //arrange
        Offer validOffer = new Offer(HIGH_AMOUNT, message,"buyerUsername");
        OfferValidator offerValidator = new OfferValidator(validOffer, product);

        //act
        Executable executable = offerValidator::validateEntity;

        //assert
        assertDoesNotThrow(executable);
    }

    @Test
    public void givenValidOfferAndProductWithSameAmount_whenValidatingEntity_thenDoesNotThrowException() {
        //arrange
        Offer validOffer = new Offer(MEDIUM_AMOUNT, message,"buyerUsername");
        OfferValidator offerValidator = new OfferValidator(validOffer, product);

        //act
        Executable executable = offerValidator::validateEntity;

        //assert
        assertDoesNotThrow(executable);
    }
}