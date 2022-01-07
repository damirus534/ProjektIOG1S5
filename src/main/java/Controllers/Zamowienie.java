package Controllers;

public class Zamowienie {
    //pola klasy zamowienia
    public int idZamowienia;
    public String loginUzytkownika;
    public String data;
    public String adres;
    public int idKontenera;
    public StatusZamowienia status;
    //Konstruktor
    public Zamowienie(int idZamowienia,String loginUzytkownika,String data,String adres,
               int idKontenera,StatusZamowienia STATUS){
        this.idZamowienia=idZamowienia;
        this.adres=adres;
        this.idKontenera=idKontenera;
        this.loginUzytkownika=loginUzytkownika;
        this.data=data;
        this.status=STATUS;
    }

    public int getIdKontenera(){
        return this.idKontenera;
    }

    public void zmienStatusZamowienia(){
        status.kolejneZamowienie();
    }
}
