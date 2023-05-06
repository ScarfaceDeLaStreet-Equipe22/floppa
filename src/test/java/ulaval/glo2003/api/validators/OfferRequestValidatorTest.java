package ulaval.glo2003.api.validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingAmountException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingMessageException;
import ulaval.glo2003.api.exceptions.OfferRequestExceptions.MissingParamBuyerUsername;
import ulaval.glo2003.api.requests.OfferRequest;

public class OfferRequestValidatorTest {

    private OfferRequest offerRequest;
    private OfferRequestValidator offerRequestValidator;
    private static String MESSAGE = "a".repeat(100);
    private static String AMOUNT = "2";
    private static String BUYERUSERNAME = "buyerUsername";
    private static String NULL_STRING = null;

    @Test
    public void givenNullAmount_whenValidatingRequest_thenThrowsMissingAmountException() {
        offerRequest = new OfferRequest(NULL_STRING, MESSAGE);
        offerRequestValidator = new OfferRequestValidator(offerRequest, BUYERUSERNAME);

        Executable executable = offerRequestValidator::validateRequest;

        assertThrows(MissingAmountException.class, executable);
    }

    @Test
    public void givenNullMessage_whenValidatingRequest_thenThrowsMissingMessageException() {
        offerRequest = new OfferRequest(AMOUNT, NULL_STRING);
        offerRequestValidator = new OfferRequestValidator(offerRequest, BUYERUSERNAME);

        Executable executable = offerRequestValidator::validateRequest;

        assertThrows(MissingMessageException.class, executable);
    }

    @Test
    public void givenNullBuyerUsername_whenValidatingRequest_thenThrowsMissingParamBuyerUsername() {
        offerRequest = new OfferRequest(AMOUNT, MESSAGE);
        offerRequestValidator = new OfferRequestValidator(offerRequest, NULL_STRING);

        Executable executable = offerRequestValidator::validateRequest;

        assertThrows(MissingParamBuyerUsername.class, executable);
    }

    @Test
    public void givenValidRequest_whenValidatingRequest_thenDoesNotThrowException() {
        offerRequest = new OfferRequest(AMOUNT, MESSAGE);
        offerRequestValidator = new OfferRequestValidator(offerRequest, BUYERUSERNAME);

        Executable executable = offerRequestValidator::validateRequest;

        assertDoesNotThrow(executable);
    }
}
