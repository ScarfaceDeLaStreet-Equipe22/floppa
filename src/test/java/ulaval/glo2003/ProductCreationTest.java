package ulaval.glo2003;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.domain.exceptions.ErrorResponse;

import static com.google.common.truth.Truth.assertThat;

public class ProductCreationTest extends FloppaApiTest
{

    private String sellerId;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();

        SellerRequest request = SellerRequestFixture.valid();
        Response sellerResponse = sendPostSeller(request);

        String sellerLocation = sellerResponse.getHeaderString("Location");
        String[] sellerLocationParts = sellerLocation.split("/");

        sellerId = sellerLocationParts[sellerLocationParts.length - 1];
    }

    @Test
    void givenValidRequest_whenCreatingProduct_thenReturns200WithRedirect()
    {
        ProductRequest request = ProductRequestFixture.valid();

        Response response = sendPostProduct(request, sellerId);

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getHeaderString("Location")).contains("/products/");
    }

    @Test
    void givenRequestWithMissingName_whenCreatingProduct_thenReturns400WithError()
    {
        ProductRequest request = ProductRequestFixture.missingField();

        Response response = sendPostProduct(request, sellerId);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    @Test
    void givenRequestWithEmptyName_whenCreatingProduct_thenReturns400WithError()
    {
        ProductRequest request = ProductRequestFixture.emptyField();

        Response response = sendPostProduct(request, sellerId);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    @Test
    void givenRequestWithInvalidPrice_whenCreatingProduct_thenReturns400WithError()
    {
        ProductRequest request = ProductRequestFixture.invalidPrice();

        Response response = sendPostProduct(request, sellerId);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    private Response sendPostProduct(ProductRequest request, String sellerId)
    {
        return target("/products")
                .request()
                .header("X-Seller-Id", sellerId)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    }

    private Response sendPostSeller(SellerRequest request)
    {
        return target("/sellers").request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    }

}
