package ulaval.glo2003.Seller;

import java.time.LocalDate;
import java.util.UUID;

public class Seller {

    public String name;
    public String bio;
    public LocalDate birthDate;
    private final String id;

    public Seller(String name, String bio, LocalDate birthDate) {
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.id = UUID.randomUUID().toString();
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
    public String getId() {
        return id;
    }
}
