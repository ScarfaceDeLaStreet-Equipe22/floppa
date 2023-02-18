package ulaval.glo2003.domain.ProductClasses;

import org.junit.jupiter.api.Test;

import java.security.PublicKey;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test
    public void AmountValidationTest(){
        Amount amount = new Amount("15") ;

        assertThat(amount.getAmount()).isEqualTo(15);
    }

}