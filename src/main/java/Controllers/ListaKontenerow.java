package Controllers;

import com.google.firebase.database.DataSnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ListaKontenerow extends Kontener{

    public Vector<Kontener> konteneryVector = new Vector<Kontener>();

    public ListaKontenerow(){

    }
    //konstruktor na podstawie hashmapy
    public ListaKontenerow(Map<String,Object> dane){
        //przetworzenie danych z bazy danych
        HashMap<String,Object> temp;

        for(Integer i=1;i<=dane.size();i++){
            if(dane.get(i.toString())!=null) {
                temp = (HashMap<String, Object>) dane.get(i.toString());
                Kontener kontener = new Kontener((boolean) temp.get("status"), (String) temp.get("dostepnosc"), (long) temp.get("id"));

                konteneryVector.add(kontener);
            }
        }

    }

    public void ListaKontenerowDodajKontener(Kontener KONTENER) {
        konteneryVector.add(KONTENER);
    }
    public Vector<Kontener> getLista(){
        return this.konteneryVector;
    }

    public String podajAktualnaDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public long wolneID(){
        long i = 1;
        for(Kontener kontener : konteneryVector){
            if(i<kontener.getIdKontenera()) i = kontener.getIdKontenera();
        }
        return i+1;
    }
}
