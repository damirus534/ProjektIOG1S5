package Controllers;

import javax.swing.*;

public class KlasaUzytkownikow {
    public String Login;
    public String Haslo;
    public grupaUzytkownika GrupaAktualnegoUzytkownika;//ogolnie koncepcja z tym enumem jest do rozkminienia, bo nie wiem jak latwiej @damian

    public enum grupaUzytkownika{
        Klient(0) , Kierowca(1), Wlasciciel(2);

        int id;
        grupaUzytkownika(int id){
            this.id=id;
        }


    };

    public KlasaUzytkownikow(String LOGIN, String HASLO, int GRUPAUZYTKOWNIKA){
        this.Login = LOGIN;
        this.Haslo = HASLO;
        for(int i=0;i<3;i++){
            if(GRUPAUZYTKOWNIKA == i){
                this.GrupaAktualnegoUzytkownika = grupaUzytkownika.values()[i];
            }
        }
    }
}
