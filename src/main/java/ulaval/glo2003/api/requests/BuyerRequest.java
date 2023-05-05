package ulaval.glo2003.api.requests;

import java.util.List;

public class BuyerRequest {

    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;
    public List<String> preferences;
    public List<String> purchases;

    public BuyerRequest(
            String name, String birthdate, String email, String phoneNumber, List<String> preferences, List<String> purchases) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.preferences = preferences;
        this.purchases = purchases;
    }

    public BuyerRequest() {}

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
