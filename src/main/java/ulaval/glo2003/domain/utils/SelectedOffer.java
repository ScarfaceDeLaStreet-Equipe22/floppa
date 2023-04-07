package ulaval.glo2003.domain.utils;

import java.util.HashMap;

public class SelectedOffer {
    public String username;
    public Double amount;
    public Boolean isValueNull;

    public SelectedOffer() {
        this.isValueNull = true;
    }

    public void acceptedOffer(String buyerUsername, Double amount){
        this.username = buyerUsername;
        this.amount = amount;
        this.isValueNull = false;
    }

    public HashMap<String, String> formatForJsonResponse(){
        if (isValueNull){
            return null;
        } else {
            HashMap<String, String> jsonResponse = new HashMap<String, String>(2);
            jsonResponse.put("username", this.username);
            jsonResponse.put("amount", String.valueOf(this.amount));
            return jsonResponse;
        }
    }
}
