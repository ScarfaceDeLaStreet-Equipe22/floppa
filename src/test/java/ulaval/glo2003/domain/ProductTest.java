package ulaval.glo2003.domain;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.domain.ProductClasses.Amount;
import ulaval.glo2003.domain.ProductClasses.ProductCategory;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
   public void getAverageAmountTest(){
       Product product = new Product("ballon","ballon de foot",
               new ProductCategory("Sport"),
               new Amount("15"),
               new Seller("ahmed",
                       "1968-05-12",
                       "ahmed@gmail.com",
                       "14185691931",
                       "je suis un vendeur sympa")
       );
       Offer offer1 = new Offer("10","offre","acheteur") ;
       Offer offer2 = new Offer("20","offre","acheteur") ;
       product.addOffer(offer1);
       product.addOffer(offer2);

       double averageOffers = product.getAverageAmountOfOffers();

       assertThat(averageOffers).isEqualTo(15);
   }

}