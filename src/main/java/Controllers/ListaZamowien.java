package Controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ListaZamowien {
    private Vector<Zamowienie> listaZanowien = new Vector<>();



    public ListaZamowien(){

    }
    //przetworzenie danych z bazy danych
    public ListaZamowien(Map<String,Object> dane){
        HashMap<String,Object> temp;
        StatusZamowienia statusZamowienia=StatusZamowienia.DostarczenieDoKlienta;
        for(Integer i=1;i<=dane.size();i++){
            if(dane.get(i.toString())!=null) {
                temp = (HashMap<String, Object>) dane.get(i.toString());
                //status pamieta� �eby doda� musi si� zgadza� z tymi nazwami
                switch ((String) temp.get("status")) {
                    case "Zakonczono":
                        statusZamowienia = StatusZamowienia.Zakonczenie;
                        break;
                    case "OczekiwaniaNaDostarczenie":
                        statusZamowienia = StatusZamowienia.OczekiwaniaNaDostarczenie;
                        break;
                    case "DostarczenieDoKlienta":
                        statusZamowienia = StatusZamowienia.DostarczenieDoKlienta;
                        break;
                    case "DostarcznieDoWysypiska":
                        statusZamowienia = StatusZamowienia.DostarcznieDoWysypiska;
                        break;
                    default:
                        statusZamowienia = StatusZamowienia.Zakonczenie;
                        break;
                }
                Zamowienie zamowienie = new Zamowienie((long) temp.get("orderID"), (String) temp.get("username"), (String) temp.get("data"),
                        (String) temp.get("adres"), (long) temp.get("containerID"), statusZamowienia);
                listaZanowien.add(zamowienie);
            }
        }

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

    public int zwrocWolneIdZamowienia(){
        int i;
        for(i=1;i<listaZanowien.size()+1;i++){
            if(listaZanowien.get(i-1).idZamowienia != i){
                return i;
            }
        }
        return i+1;
    }

    public Vector<Zamowienie> getListaZanowien(){
        return this.listaZanowien;
    }
    public int znajdzZamowienie(String adres,int idKontenera){
        for(Zamowienie zam1 : this.listaZanowien){
            if(zam1.getIdKontenera()==idKontenera&&zam1.getAdres()==adres){
                return zam1.idZamowienia;
            }
        }
        return -1;
    }
    public Zamowienie getZamowienie(int id){
        for(Zamowienie zamowienie:listaZanowien){
            if(zamowienie.idZamowienia==id)return zamowienie;
        }
        return null;
    }
}
