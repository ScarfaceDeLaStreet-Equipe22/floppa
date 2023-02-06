package ulaval.glo2003.Domain;

import ulaval.glo2003.api.SellerExceptions.*;
import ulaval.glo2003.api.Utils.InvalidNameException;
import ulaval.glo2003.api.Utils.MissingNameException;

import java.time.LocalDate;

public class SellerParamsValidator {
    public String name;
    public String bio;
    public LocalDate birthdate;

    public SellerParamsValidator(String name, String bio, String birthdate, String email, String phoneNumber) {
        assertParamNotNull(name, bio, birthdate, email, phoneNumber);
        assertParamNotEmpty(name, bio, birthdate, email, phoneNumber);
        this.name = name;
        this.bio = bio;
        this.birthdate = LocalDate.parse(birthdate);
    }

    private void assertParamNotEmpty(String name, String bio, String birthdate, String email, String phoneNumber) {
        if (name.isEmpty()) {
            throw new InvalidNameException();
        }
        if (bio.isEmpty()) {
            throw new InvalidBioException();
        }
        if (birthdate.isEmpty()) {
            throw new InvalidBirthdateException();
        }
        if (email.isEmpty()) {
            throw new InvalidEmailException();
        }
        if (phoneNumber.isEmpty()) {
            throw new InvalidPhoneNumberException();
        }
    }

    private void assertParamNotNull(String name, String bio, String birthdate, String email, String phoneNumber) {
        if (name == null) {
            throw new MissingNameException();
        }
        if (bio == null) {
            throw new MissingBioException();
        }
        if (birthdate == null) {
            throw new MissingBirthDateException();
        }
        if (email == null) {
            throw new MissingEmailException();
        }
        if (phoneNumber == null) {
            throw new MissingPhoneNumberException();
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
