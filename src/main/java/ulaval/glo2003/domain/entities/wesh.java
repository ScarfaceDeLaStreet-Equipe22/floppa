package ulaval.glo2003.domain.entities;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Sellers")
public class wesh {

    @Id
    private final String lol;

    public wesh(String lol) {
        this.lol = lol;
    }
}
