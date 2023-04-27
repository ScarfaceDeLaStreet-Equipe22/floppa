package ulaval.glo2003;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.requests.BuyerRequest;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.domain.exceptions.ErrorResponse;

import static com.google.common.truth.Truth.assertThat;

public class BuyerCreationTest extends FloppaApiTest
{

    @Test
    void givenValidRequest_whenCreatingBuyer_thenReturns200WithRedirect()
    {
        BuyerRequest request = BuyerRequestFixture.valid();

        Response response = sendBuyerSeller(request);

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getHeaderString("Location")).contains("/buyers/");
    }

    @Test
    void givenRequestWithMissingName_whenCreatingBuyer_thenReturns400WithError()
    {
        BuyerRequest request = BuyerRequestFixture.missingField();

        Response response = sendBuyerSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    @Test
    void givenRequestWithEmptyName_whenCreatingBuyer_thenReturns400WithError()
    {
        BuyerRequest request = BuyerRequestFixture.emptyField();

        Response response = sendBuyerSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    @Test
    void givenRequestWithInvalidBirthdate_whenCreatingBuyer_thenReturns400WithError()
    {
        BuyerRequest request = BuyerRequestFixture.tooYoung();

        Response response = sendBuyerSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    private Response sendBuyerSeller(BuyerRequest request)
    {
        return target("/buyers").request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    }

}
