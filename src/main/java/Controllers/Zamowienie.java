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
    public Zamowienie(long idZamowienia,String loginUzytkownika,String data,String adres,
               long idKontenera,StatusZamowienia STATUS){
        this.idZamowienia=(int)idZamowienia;
        this.adres=adres;
        this.idKontenera=(int)idKontenera;
        this.loginUzytkownika=loginUzytkownika;
        this.data=data;
        this.status=STATUS;
    }

    public int getIdKontenera(){
        return this.idKontenera;
    }
    public String getAdres(){return this.adres;}
    public String getData(){return  this.data;}
    public StatusZamowienia getStatus(){return status;}


    public void zmienStatusZamowienia(){
        status.kolejnyStatusZamowienia();
    }
}
