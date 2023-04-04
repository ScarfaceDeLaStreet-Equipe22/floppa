package ulaval.glo2003;

import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class CreationVendeurTests extends JerseyTest
{

    // FOR JUNIT 5
    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
    // END FOR

    @Override
    protected Application configure() {
        return new ResourceConfigProvider().provide();
    }


}
