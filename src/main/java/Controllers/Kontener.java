package Controllers;

import java.util.Vector;

public class Kontener {
    public boolean status;
    public String najblizszaDostepnosc;//zmienic trzeba pozniej na private wszystko
    public int idKontenera;


    public Kontener(boolean STATUS, String NAJBLIZSZADOSTEPNOSC, int IDKONTENERA){
        this.status = STATUS;
        this.najblizszaDostepnosc = NAJBLIZSZADOSTEPNOSC;
        this.idKontenera = IDKONTENERA;
    }

    public Kontener() {//konstruktor potrzebny aby zrobic taki sam w klasie listakontenerow

    }
}
