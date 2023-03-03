package ulaval.glo2003.api.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingAmountException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingMessageException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingParamBuyerUsername;
import ulaval.glo2003.api.requests.OfferRequest;

import static org.junit.jupiter.api.Assertions.*;

public class OfferRequestValidatorTest {

    private OfferRequest offerRequest;
    private OfferRequestValidator offerRequestValidator;
    private static String MESSAGE = "a".repeat(100);
    private static String AMOUNT = "2";
    private static String BUYERUSERNAME = "buyerUsername";
    private static String NULL_STRING = null;

    @Test
    public void givenNullAmount_whenValidatingRequest_thenThrowsMissingAmountException() {
        // arrange
        offerRequest = new OfferRequest(NULL_STRING, MESSAGE);
        offerRequestValidator = new OfferRequestValidator(offerRequest, BUYERUSERNAME);

        // act
        Executable executable = offerRequestValidator::validateRequest;

        // assert
        assertThrows(MissingAmountException.class, executable);
    }

    @Test
    public void givenNullMessage_whenValidatingRequest_thenThrowsMissingMessageException() {
        // arrange
        offerRequest = new OfferRequest(AMOUNT, NULL_STRING);
        offerRequestValidator = new OfferRequestValidator(offerRequest, BUYERUSERNAME);

        // act
        Executable executable = offerRequestValidator::validateRequest;

        // assert
        assertThrows(MissingMessageException.class, executable);
    }

    @Test
    public void givenNullBuyerUsername_whenValidatingRequest_thenThrowsMissingParamBuyerUsername() {
        // arrange
        offerRequest = new OfferRequest(AMOUNT, MESSAGE);
        offerRequestValidator = new OfferRequestValidator(offerRequest, NULL_STRING);

        // act
        Executable executable = offerRequestValidator::validateRequest;

        // assert
        assertThrows(MissingParamBuyerUsername.class, executable);
    }

    @Test
    public void givenValidRequest_whenValidatingRequest_thenDoesNotThrowException() {
        // arrange
        offerRequest = new OfferRequest(AMOUNT, MESSAGE);
        offerRequestValidator = new OfferRequestValidator(offerRequest, BUYERUSERNAME);

        // act
        Executable executable = offerRequestValidator::validateRequest;

        // assert
        assertDoesNotThrow(executable);
    }
}