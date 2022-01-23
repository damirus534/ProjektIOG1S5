package Model;
import Controllers.ListaZamowien;
import Controllers.Zamowienie;
import DB.dataBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ModelTest {
    @Test
    void listaTest(){
        ListaZamowien listaZamowien=new ListaZamowien(new dataBase().table("orders").list());
        Zamowienie zamowienie=listaZamowien.getZamowienie(2);
        int i=listaZamowien.znajdzZamowienie(zamowienie.adres, zamowienie.idKontenera)-1;
        assertEquals(zamowienie.getAdres(),listaZamowien.getZamowienie(i).getAdres());

    }
    @Test
    void kolejnoscTest(){
        ListaZamowien listaZamowien=new ListaZamowien(new dataBase().table("orders").list());
        Zamowienie zamowienie=listaZamowien.getZamowienie(2);
        listaZamowien.zmienKolejnosc(2,4);
        assertEquals(listaZamowien.getZamowienie(4).getAdres(),zamowienie.getAdres());
    }
}
