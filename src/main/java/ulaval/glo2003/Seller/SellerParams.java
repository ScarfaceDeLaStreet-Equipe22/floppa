package ulaval.glo2003.Seller;

import ulaval.glo2003.Seller.Exceptions.InvalidBioException;
import ulaval.glo2003.Seller.Exceptions.InvalidBirthdateException;
import ulaval.glo2003.Seller.Exceptions.MissingBioException;
import ulaval.glo2003.Seller.Exceptions.MissingBirthDateException;
import ulaval.glo2003.Utils.InvalidNameException;
import ulaval.glo2003.Utils.MissingNameException;

import java.time.LocalDate;

public class SellerParams {
    public String name;
    public String bio;
    public LocalDate birthDate;

    public SellerParams(String name, String bio, String birthDate) {
        assertParamNotNull(name, bio, birthDate);
        assertParamNotEmpty(name, bio, birthDate);
        this.name = name;
        this.bio = bio;
        this.birthDate = LocalDate.parse(birthDate);
    }

    private void assertParamNotEmpty(String name, String bio, String birthDate) {
        if (name.isEmpty()) {
            throw new InvalidNameException();
        }
        if (bio.isEmpty()) {
            throw new InvalidBioException();
        }
        if (birthDate.isEmpty()) {
            throw new InvalidBirthdateException();
        }

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
