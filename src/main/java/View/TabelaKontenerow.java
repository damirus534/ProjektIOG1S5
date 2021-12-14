package View;

import Controllers.Kontener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;

public class TabelaKontenerow {

    public TabelaKontenerow(){
        nazwyKolumn.add("nr_Kontenera");
        nazwyKolumn.add("najblizsza_dostepnosc");
        nazwyKolumn.add("Status");

        Vector<String>daneDoTabeli1 = new Vector<String>();
        daneDoTabeli1.add(String.valueOf(kont.konteneryVector.get(0).idKontenera));
        daneDoTabeli1.add(kont.najblizszaDostepnosc);
        daneDoTabeli1.add(String.valueOf(kont.konteneryVector.get(0).status));
        daneDoTabeli.add(daneDoTabeli1);
        kont.konteneryVector.add(new Kontener(false, "od wczoraj", 2));
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

    private Vector<Vector<String>> daneDoTabeli = new Vector<Vector<String>>();
    private Vector<String> nazwyKolumn = new Vector<String>(3);
    private TableModel modelTabeli;
    private JTable tabela;
    private Controllers.Kontener kont = new Kontener(true, "od jutra", 1);

    public JTable getTabela() {
        return tabela;
    }
}
