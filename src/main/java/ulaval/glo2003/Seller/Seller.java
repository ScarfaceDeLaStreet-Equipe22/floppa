package ulaval.glo2003.Seller;

import java.time.LocalDate;

public class Seller {

    public String name;
    public String bio;
    public LocalDate birthDate;

    public Seller(String name, String bio, LocalDate birthDate) {
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
    }


    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


}
