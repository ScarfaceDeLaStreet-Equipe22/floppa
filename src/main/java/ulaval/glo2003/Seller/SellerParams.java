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
    public LocalDate birthdate;

    public SellerParams(String name, String bio, String birthdate) {
        assertParamNotNull(name, bio, birthdate);
        assertParamNotEmpty(name, bio, birthdate);
        this.name = name;
        this.bio = bio;
        this.birthdate = LocalDate.parse(birthdate);
    }

    private void assertParamNotEmpty(String name, String bio, String birthdate) {
        if (name.isEmpty()) {
            throw new InvalidNameException();
        }
        if (bio.isEmpty()) {
            throw new InvalidBioException();
        }
        if (birthdate.isEmpty()) {
            throw new InvalidBirthdateException();
        }

    }

    private void assertParamNotNull(String name, String bio, String birthdate) {
        if (name == null) {
            throw new MissingNameException();
        }
        if (bio == null) {
            throw new MissingBioException();
        }
        if (birthdate == null) {
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
        return birthdate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthdate = birthdate;
    }
}
