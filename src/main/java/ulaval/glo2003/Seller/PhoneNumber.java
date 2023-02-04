package ulaval.glo2003.Seller;

public class PhoneNumber {
    private final String numero ;
    public PhoneNumber(String numero) {
        if (phoneNumberValidator(numero)){
            this.numero = numero;
        } else {
            throw new RuntimeException("erreur");
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
