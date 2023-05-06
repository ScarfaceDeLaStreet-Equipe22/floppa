package ulaval.glo2003.domain.utils;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Entity
public class SelectedOffer {
    public String username;
    public Double amount;
    public Boolean isValueNull;
    @Id
    public String id;

    public SelectedOffer() {
        this.isValueNull = true;
    }

    public void acceptedOffer(String buyerUsername, Double amount){
        this.username = buyerUsername;
        this.amount = amount;
        this.isValueNull = false;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectedOffer that = (SelectedOffer) o;
        return Objects.equals(username, that.username) && Objects.equals(amount, that.amount) && Objects.equals(isValueNull, that.isValueNull) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, amount, isValueNull, id);
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
