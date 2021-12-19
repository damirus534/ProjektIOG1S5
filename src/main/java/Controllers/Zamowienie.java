package Controllers;

public class Zamowienie {
    //pola klasy zamowienia
    private int idZamowienia;
    private String loginUzytkownika;
    private String data;
    private String adres;
    private  int idKontenera;
    private  StatusZamowienia status;
    //Konstruktor
    Zamowienie(int idZamowienia,String loginUzytkownika,String data,String adres,
               int idKontenera,StatusZamowienia status){
        this.idZamowienia=idZamowienia;
        this.adres=adres;
        this.idKontenera=idKontenera;
        this.loginUzytkownika=loginUzytkownika;
        this.data=data;
        this.status=status;
    }
}
