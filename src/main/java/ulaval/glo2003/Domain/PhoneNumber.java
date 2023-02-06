package ulaval.glo2003.Domain;

import ulaval.glo2003.api.Utils.InvalidParamException;

public class PhoneNumber {
    private final String numero ;
    public PhoneNumber(String numero) {
        if (phoneNumberValidator(numero)){
            this.numero = numero;
        } else {
            throw new InvalidParamException("invalid phone number");
        }
    }


    public String getNumero() {
        return numero;
    }


    private boolean phoneNumberValidator(String numero){
        boolean reponse = true ;
        for (int i = 0  ; i <= numero.length() ; i++){
            char we = numero.charAt(i) ;
            if (Character.compare(we,' ') == 0){
                reponse = false ;
            }
            if (!Character.isDigit(we)){
               reponse = false ;
            }
        }
        if (numero.length() != 11){
            reponse = false ;
        }

        return reponse;

    }
}
