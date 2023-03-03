package ulaval.glo2003.api.requests;

public class SellerRequest {
    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
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

    public String name;
    public String bio;
    public String birthdate;
    public String email;
    public String phoneNumber;

    public SellerRequest() {}
}
