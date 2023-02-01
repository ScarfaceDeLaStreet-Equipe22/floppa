package ulaval.glo2003.Seller;

import ulaval.glo2003.Utils.MissingNameException;

import java.time.LocalDate;
import java.util.Locale;

public class SellerParams {
    private String name;
    private String bio;
    private LocalDate birthDate;

    public SellerParams(String name, String bio, String birthDate) {
        assertParamNotNull(name, bio, birthDate);

        this.name = name;
        this.bio = bio;
        this.birthDate = LocalDate.parse(birthDate);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
