package View;

import Controllers.Kontener;
import Controllers.ListaKontenerow;
import DB.dataBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TabelaKontenerow {

    public TabelaKontenerow(){
        nazwyKolumn.add("nr_Kontenera");
        nazwyKolumn.add("najblizsza_dostepnosc");
        nazwyKolumn.add("Status");

        Vector<String>daneDoTabeli1 = new Vector<String>();

        kont.ListaKontenerowDodajKontener(new Kontener(true,"od jutra", 1));
        daneDoTabeli1.add(String.valueOf(kont.konteneryVector.get(0).idKontenera));
        daneDoTabeli1.add(kont.konteneryVector.get(0).najblizszaDostepnosc);
        daneDoTabeli1.add(String.valueOf(kont.konteneryVector.get(0).status));
        daneDoTabeli.add(daneDoTabeli1);
        kont.ListaKontenerowDodajKontener(new Kontener(false, "od wczoraj", 2));
        Vector<String>daneDoTabeli2 = new Vector<String>();
        daneDoTabeli2.add(String.valueOf(kont.konteneryVector.get(1).idKontenera));
        daneDoTabeli2.add(kont.konteneryVector.get(1).najblizszaDostepnosc);
        daneDoTabeli2.add(String.valueOf(kont.konteneryVector.get(1).status));
        daneDoTabeli.add(daneDoTabeli2);

        //tworzenie listy w panelu w ktorym bedzie sie wyswietlac lista
        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0,0,600,600);
        tabela.setEnabled(false);
        tabela.setVisible(true);
    };
    //konstruktor uzywajacy danych z bazy danych
    public TabelaKontenerow(ListaKontenerow lista){
        this.kont=lista;
        nazwyKolumn.add("nr_Kontenera");
        nazwyKolumn.add("najblizsza_dostepnosc");
        nazwyKolumn.add("Status");

        Vector<String>daneDoTabeli1 = new Vector<String>();
        //dodawanie wartosci do wektora tabeli
        for(int i=0;i<kont.getLista().size();i++){
            daneDoTabeli1=new Vector<>();

            daneDoTabeli1.add(String.valueOf(kont.getLista().get(i).idKontenera));
            daneDoTabeli1.add(kont.getLista().get(i).getNajblizszaDostepnosc());
            daneDoTabeli1.add(String.valueOf(kont.getLista().get(i).getStatus()));


            daneDoTabeli.add(daneDoTabeli1);

        }
        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0,0,600,600);
        tabela.setEnabled(false);
        tabela.setVisible(true);
    }

    private Vector<Vector<String>> daneDoTabeli = new Vector<Vector<String>>();
    private Vector<String> nazwyKolumn = new Vector<String>(3);
    private TableModel modelTabeli;
    private JTable tabela;
    private Controllers.ListaKontenerow kont = new ListaKontenerow();

    public JTable getTabela() {
        return tabela;
    }

    public void dodawanieKonteneru(){
        Kontener temp = new Kontener(true, kont.podajAktualnaDate(), kont.wolneID());
        kont.ListaKontenerowDodajKontener(temp);
        Vector<String>daneDoTabeli1=new Vector<>();
        daneDoTabeli1.add(String.valueOf(temp.idKontenera));
        daneDoTabeli1.add(temp.najblizszaDostepnosc);
        daneDoTabeli1.add(String.valueOf(temp.status));
        daneDoTabeli.add(daneDoTabeli1);
        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        dataBase db = new dataBase();

        Map<String, Object> data = new HashMap<>();
        data.put("dostepnosc", String.valueOf(temp.najblizszaDostepnosc));
        data.put("status", temp.status);
        data.put("id", temp.idKontenera);

        db.table("containers").add(String.valueOf(temp.idKontenera), data);
    }
}
