package Controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ListaZamówień {
    private Vector<Zamowienie> listaZanowien = new Vector<>();



    public ListaZamówień(){

    }
    //przetworzenie danych z bazy danych
    public ListaZamówień(Map<String,Object> dane){
        HashMap<String,Object> temp;
        StatusZamowienia statusZamowienia=StatusZamowienia.DostarczenieDoKlienta;
        for(Integer i=1;i<=dane.size();i++){
            System.out.println(dane.toString());
            temp= (HashMap<String, Object>) dane.get(i.toString());
            //dokonczyc
            /*switch((String) temp.get("status")){
                case "Zakonczono":statusZamowienia=StatusZamowienia.Zakonczenie;break;
                case "OczekiwaniaNaDostarczenie":statusZamowienia=StatusZamowienia.OczekiwaniaNaDostarczenie;break;
                case "DostarczenieDoKlienta":statusZamowienia=StatusZamowienia.DostarczenieDoKlienta;break;
                case "DostarcznieDoWysypiska":statusZamowienia=StatusZamowienia.DostarcznieDoWysypiska;break;
            }*/
            Zamowienie zamowienie=new Zamowienie((long)temp.get("orderID"),(String)temp.get("username"),(String) temp.get("data"),(String) temp.get("adres"),(long)temp.get("containerID")
                    ,statusZamowienia);
            listaZanowien.add(zamowienie);
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
}
