package ulaval.glo2003.Seller;

import ulaval.glo2003.util.InvalidParamException;
import ulaval.glo2003.util.MissingBioException;
import ulaval.glo2003.util.MissingBirthDateException;
import ulaval.glo2003.util.MissingNameException;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SellerParams {
    private String name;
    private String bio;
    private LocalDate birthDate;

    public SellerParams(String name, String bio, String birthDate){
        assertParamNotNull(name, bio, birthDate);
        assertValidBirthDateFormat(birthDate);

        LocalDate convertedBirthDate =LocalDate.parse(birthDate);

        this.name = name;
        this.bio = bio;
        this.birthDate = convertedBirthDate;
    }

    private void assertParamNotNull(String name, String bio, String birthDate) {
        if (name == null) {
            throw new MissingNameException();
        }
        if (bio == null) {
            throw new MissingBioException();
        }
        if (birthDate == null) {
            throw new MissingBirthDateException();
        }
    }

    private void assertValidBirthDateFormat(String birthDate) {
        try {
            LocalDate.parse(
                    birthDate,
                    DateTimeFormatter.ISO_LOCAL_DATE
            );
        } catch (Exception e) {
            throw new InvalidParamException("Invalid birthdate.");
        }
    }
}
