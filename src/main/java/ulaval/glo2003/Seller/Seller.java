package ulaval.glo2003.Seller;

import java.time.Instant;
import java.time.LocalDate;

public class Seller {

    private final String name;
    private final String bio;
    private final String birthDate;

    public Seller(String name, String bio, String birthDate) {
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


}
