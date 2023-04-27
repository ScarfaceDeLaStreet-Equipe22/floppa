package ulaval.glo2003.api.requests;

public class BuyerRequest {

    public String name;
    public String birthdate;
    public String email;
    public String phoneNumber;

    public BuyerRequest(
            String name, String birthdate, String email, String phoneNumber) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
