package ulaval.glo2003.Seller;

import java.time.Instant;
import java.time.LocalDate;

public class Seller {

    private final String id;
    private final String name;
    private final String bio;
    private final LocalDate birthDate;
    private final Instant createdAt;
    public Seller(String name, String bio, LocalDate birthDate, String id, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
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

    public Instant getCreatedAt() {
        return createdAt;
    }
}
