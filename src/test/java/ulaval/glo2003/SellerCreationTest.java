package ulaval.glo2003;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.domain.exceptions.ErrorResponse;

import static com.google.common.truth.Truth.assertThat;

public class SellerCreationTest extends FloppaApiTest
{

    @Test
    void givenValidRequest_whenCreatingSeller_thenReturns200WithRedirect()
    {
        SellerRequest request = SellerRequestFixture.valid();

        Response response = sendPostSeller(request);

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getHeaderString("Location")).contains("/sellers/");
    }

    @Test
    void givenRequestWithMissingName_whenCreatingSeller_thenReturns400WithError()
    {
        SellerRequest request = SellerRequestFixture.missingField();

        Response response = sendPostSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    @Test
    void givenRequestWithEmptyName_whenCreatingSeller_thenReturns400WithError()
    {
        SellerRequest request = SellerRequestFixture.emptyField();

        Response response = sendPostSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    @Test
    void givenRequestWithInvalidBirthdate_whenCreatingSeller_thenReturns400WithError()
    {
        SellerRequest request = SellerRequestFixture.tooYoung();

        Response response = sendPostSeller(request);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    private Response sendPostSeller(SellerRequest request)
    {
        return target("/sellers").request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    }

}
