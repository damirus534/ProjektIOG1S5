package Controllers;

import java.util.Vector;

public class Kontener {//zrezygnowalem z tego pomyslu o ktorym mowilem w niedziele, nie zrobilem klasy ktora dziedziczy po kontenerze, bo sie nie oplaca dla dwoch zmiennych robic klasy
    public boolean status;
    public String najblizszaDostepnosc;//zmienic trzeba pozniej na private wszystko
    public int idKontenera;

    public Vector<Kontener> konteneryVector = new Vector<Kontener>();

    public Kontener(boolean STATUS, String NAJBLIZSZADOSTEPNOSC, int IDKONTENERA){
        this.status = STATUS;
        this.najblizszaDostepnosc = NAJBLIZSZADOSTEPNOSC;
        this.idKontenera = IDKONTENERA;
        this.konteneryVector.add(this);
    }
}
