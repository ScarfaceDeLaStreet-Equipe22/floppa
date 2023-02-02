package ulaval.glo2003.Seller;

import ulaval.glo2003.Utils.InvalidParamException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class SellerFactory {
    private final int legalAge = 18;

    private void assertParamNotBlank(SellerParams sellerParams) {
        if (sellerParams.getName().isBlank()) {
            throw new InvalidParamException("Invalid name.");
        }
        if (sellerParams.getBio().isBlank()) {
            throw new InvalidParamException("Invalid bio.");
        }
    }

    private void assertValidBirthDate(LocalDate birthDate) {
        Period sellerAge = Period.between(birthDate, LocalDate.now());
        if (sellerAge.getYears() < legalAge) {
            throw new InvalidParamException("Invalid birthdate.");
        }
    }

    public Seller makeSeller(SellerParams sellerParams) {
        assertParamNotBlank(sellerParams);
        assertValidBirthDate(sellerParams.getBirthDate());

        Seller seller =
                new Seller(
                        sellerParams.getName(),
                        sellerParams.getBio(),
                        sellerParams.getBirthDate()
                );
        return seller;
    }
}
