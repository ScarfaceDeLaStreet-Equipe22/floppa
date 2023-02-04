package ulaval.glo2003.Seller;

import java.time.Instant;
import java.util.List;

public class SellerResponse {
    public String id;
    public String name;
    public Instant createdAt;
    public String bio;

    public SellerResponse(
            String id,
            String name,
            String bio
    ) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

}
