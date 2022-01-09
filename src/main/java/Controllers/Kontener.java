package Controllers;

import java.util.Vector;

public class Kontener {
    public boolean status;
    public String najblizszaDostepnosc;//zmienic trzeba pozniej na private wszystko
    public int idKontenera;


    public Kontener(boolean STATUS, String NAJBLIZSZADOSTEPNOSC, long IDKONTENERA){
        this.status = STATUS;
        this.najblizszaDostepnosc = NAJBLIZSZADOSTEPNOSC;
        this.idKontenera = (int) IDKONTENERA;
    }
    public boolean getStatus(){
        return this.status;
    }
    public String getNajblizszaDostepnosc(){
        return this.najblizszaDostepnosc;
    }
    public int getIdKontenera(){
        return this.idKontenera;
    }

    public Kontener() {//konstruktor potrzebny aby zrobic taki sam w klasie listakontenerow

    }
}
