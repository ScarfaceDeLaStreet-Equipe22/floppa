package ulaval.glo2003;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.api.requests.ProductRequest;
import ulaval.glo2003.api.requests.SellerRequest;
import ulaval.glo2003.api.responses.SellerResponse;
import ulaval.glo2003.domain.exceptions.ErrorResponse;

import static com.google.common.truth.Truth.assertThat;

public class SellerObtentionTest extends FloppaApiTest
{

    private String existingSellerId;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();

        SellerRequest sellerRequest = SellerRequestFixture.valid();
        Response sellerRequestResponse = sendPostSeller(sellerRequest);

        String sellerLocation = sellerRequestResponse.getHeaderString("Location");
        String[] sellerLocationParts = sellerLocation.split("/");
        existingSellerId = sellerLocationParts[sellerLocationParts.length - 1];

        ProductRequest productRequest = new ProductRequest("TestProduct", "TestDescription", "other", "1.0");
        sendPostProduct(productRequest, existingSellerId);
    }

    @Test
    void givenExistingId_whenGettingSeller_thenReturns200WithSellerResponse()
    {
        String sellerId = existingSellerId;

        Response response = sendGetSeller(sellerId);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(SellerResponse.class)).isInstanceOf(SellerResponse.class);
    }

    @Test
    void givenNonExistingId_whenGettingSeller_thenReturns404WithError()
    {
        String nonExistingId = changeLastCharacter(existingSellerId);

        Response response = sendGetSeller(nonExistingId);

        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.readEntity(ErrorResponse.class)).isInstanceOf(ErrorResponse.class);
    }

    private String changeLastCharacter(String str)
    {
        int lastCharIndex = str.length() - 1;
        char lastChar = str.charAt(lastCharIndex);

        lastChar = lastChar == 'x' ? 'y' : 'x';

        return existingSellerId.substring(0, lastCharIndex - 1) + lastChar;
    }

    private Response sendGetSeller(String sellerId)
    {
        return target("/sellers/" + sellerId).request().get();
    }

    private Response sendPostProduct(ProductRequest request, String sellerId)
    {
        return target("/products/")
                .request()
                .header("X-Seller-Id", sellerId)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    }

    private Response sendPostSeller(SellerRequest request)
    {
        return target("/sellers").request().post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    }

}
