package View;

import Controllers.ListaZamowien;
import Controllers.StatusZamowienia;
import Controllers.Zamowienie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;

public class TabelaKursow {

    public TabelaKursow(){
        nazwyKolumn.add("data");
        nazwyKolumn.add("Status zamowienia");
        nazwyKolumn.add("id kontenera");


        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0,0, 600, 600);
        tabela.setEnabled(false);
        tabela.setVisible(true);


    };
    public TabelaKursow(ListaZamowien listaZamowien){
        //tworzenie na tabeli na podstawie bazy danych i stworzonej zmiennej lista zamowien
        this.kursy=listaZamowien;
        nazwyKolumn.add("data");
        nazwyKolumn.add("Status zamowienia");
        nazwyKolumn.add("id kontenera");
    //dodawanie do wektora tabeli
        Vector<String> daneDoTabeli1 = new Vector<>();

        for(int i=0;i<kursy.getListaZanowien().size();i++){
            if(kursy.getListaZanowien().get(i).status.name()!="Zakonczenie") {
                daneDoTabeli1 = new Vector<>();
                daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(i).data));
                daneDoTabeli1.add(kursy.getListaZanowien().get(i).status.name());
                daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(i).adres));
                daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(i).idKontenera));
                daneDoTabeli1.add(kursy.getListaZanowien().get(i).status.name());
                if (java.time.LocalDate.now().toString().equals(listaZamowien.getListaZanowien().get(i).getData()))
                    aktualne.add(daneDoTabeli1);
                else
                    daneDoTabeli.add(daneDoTabeli1);
            }

        }
        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0,0, 600, 600);
        tabela.setEnabled(false);
        tabela.setVisible(true);

    }
    void zmienStatusKursu(){

    }


    private JTable tabela;
    private TableModel modelTabeli;
    private Vector<Vector<String>> daneDoTabeli = new Vector<>();
    private Vector<String> nazwyKolumn = new Vector<>();
    private Controllers.ListaZamowien kursy = new ListaZamowien();
    Vector<Vector<String>> aktualne=new Vector<>();
    public JTable getTabela(){
        return this.tabela;
    }
    public Vector<Vector<String>> getAktualne(){

        Vector<Vector<String>> temp1 = new Vector<>();

        for(int i=0;i<aktualne.size();i++){
            Vector<String> temp2 = new Vector<>();
            temp2.add(aktualne.get(i).get(2));
            temp2.add(aktualne.get(i).get(3));
            temp2.add(aktualne.get(i).get(0));
            temp1.add(temp2);
        }

        return temp1;
    }
}