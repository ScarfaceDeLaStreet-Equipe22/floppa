package ulaval.glo2003.domain.ProductClasses;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductCategoryTest {

    @Test
    public void productCategoryValidatorTest() {

        ProductCategory productCategory = new ProductCategory("other");

        assertThat(productCategory.getCategory()).isEqualTo("other");
    }
}
