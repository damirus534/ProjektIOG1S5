package View;

import Controllers.ListaZamówień;
import Controllers.StatusZamowienia;
import Controllers.Zamowienie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;

import static Controllers.StatusZamowienia.DostarczenieDoKlienta;

public class TabelaKursow {

    public TabelaKursow(){
        nazwyKolumn.add("data");
        nazwyKolumn.add("Status zamowienia");
        nazwyKolumn.add("id kontenera");

        Vector<String> daneDoTabeli1 = new Vector<>();

        kursy.dodajZamowienie(new Zamowienie(1, "user1", "07.01.2022", "Krasinskiego8", 1, StatusZamowienia.Zakonczenie));

        daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(0).data));
        daneDoTabeli1.add(kursy.getListaZanowien().get(0).status.name());
        daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(0).idKontenera));
        daneDoTabeli.add(daneDoTabeli1);

        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0,0, 600, 600);
        tabela.setEnabled(false);
        tabela.setVisible(true);
        daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(0).data));
        daneDoTabeli1.add(kursy.getListaZanowien().get(0).status.name());
        daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(0).idKontenera));
        daneDoTabeli.add(daneDoTabeli1);

    };
    public TabelaKursow(ListaZamówień listaZamówień){
        //tworzenie na tabeli na podstawie bazy danych i stworzonej zmiennej lista zamowien
        kursy=listaZamówień;
        nazwyKolumn.add("data");
        nazwyKolumn.add("Status zamowienia");
        nazwyKolumn.add("id kontenera");
    //dodawanie do wektora tabeli
        Vector<String> daneDoTabeli1 = new Vector<>();
        for(int i=0;i<kursy.getListaZanowien().size();i++){
            daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(0).data));
            daneDoTabeli1.add(kursy.getListaZanowien().get(0).status.name());
            daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(0).idKontenera));
            daneDoTabeli.add(daneDoTabeli1);
        }
        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0,0, 600, 600);
        tabela.setEnabled(false);
        tabela.setVisible(true);
        daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(0).data));
        daneDoTabeli1.add(kursy.getListaZanowien().get(0).status.name());
        daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(0).idKontenera));
        daneDoTabeli.add(daneDoTabeli1);
    }


    private JTable tabela;
    private TableModel modelTabeli;
    private Vector<Vector<String>> daneDoTabeli = new Vector<>();
    private Vector<String> nazwyKolumn = new Vector<>();
    private Controllers.ListaZamówień kursy = new ListaZamówień();

    public JTable getTabela(){
        return this.tabela;
    }
}
