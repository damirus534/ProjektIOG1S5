package Controllers;

import com.google.firebase.database.DataSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ListaKontenerow extends Kontener{

    public Vector<Kontener> konteneryVector = new Vector<Kontener>();

    public ListaKontenerow(){

    }
    //konstruktor na podstawie hashmapy
    public ListaKontenerow(Map<String,Object> dane){
        //przetworzenie danych z bazy danych
        HashMap<String,Object> temp;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDate = LocalDate.now();
        LocalDate tmpDate = null;
        System.out.println(dtf.format(localDate));
        for(Integer i=1, j = 1; i <= dane.size(); i++, j++) {
            if (dane.get(j.toString()) != null) {
                temp = (HashMap<String, Object>) dane.get(j.toString());
                Kontener kontener = new Kontener((boolean) temp.get("status"), (String) temp.get("dostepnosc"), (long) temp.get("id"));
                tmpDate = LocalDate.parse(kontener.najblizszaDostepnosc);
                if(localDate.isAfter(tmpDate)){
                    kontener.najblizszaDostepnosc = localDate.toString();
                }
                konteneryVector.add(kontener);
            } else if (i != dane.size() - 1) {
                i--;
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
