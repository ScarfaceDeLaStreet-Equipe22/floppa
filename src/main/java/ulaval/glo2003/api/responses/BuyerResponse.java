package ulaval.glo2003.api.responses;

import ulaval.glo2003.domain.utils.DateTime;

import java.util.List;

public class BuyerResponse {

    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public String createdAt;
    public String id;
    public List<String> preferences;
    public List<String> purchases;

    public BuyerResponse(
            String id,
            String name,
            String birthDate,
            String email,
            String phoneNumber,
            DateTime createdAt,
            List<String> preferences,
            List<String> purchases) {
        this.id = id;
        this.name = name;
        this.birthdate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt.getDateTime();
        this.preferences = preferences;
        this.purchases = purchases;
    }

    public BuyerResponse() {}
}
