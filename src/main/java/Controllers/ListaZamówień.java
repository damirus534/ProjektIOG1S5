package Controllers;

import java.util.Map;
import java.util.Vector;

public class ListaZamówień {
    private Vector<Zamowienie> listaZanowien = new Vector<>();

    ListaZamówień(Map<String,Object> dane){
        //wczytanie z bazy danych do listy zamowien;
    }

    public ListaZamówień(){

    }

    public void dodajZamowienie(Zamowienie zamowienie){
        listaZanowien.add(zamowienie);
    }

    public void zmienStatusZamowienia(int idZamowienia){
        for(Zamowienie element : listaZanowien){
            if(element.getIdKontenera() == idZamowienia){
                element.zmienStatusZamowienia();
            }
        }
    }

    public Vector<Zamowienie> getListaZanowien(){
        return this.listaZanowien;
    }
}
