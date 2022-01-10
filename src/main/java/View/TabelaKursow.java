package View;

import Controllers.ListaZam�wie�;
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
    public TabelaKursow(ListaZam�wie� listaZam�wie�){
        //tworzenie na tabeli na podstawie bazy danych i stworzonej zmiennej lista zamowien
        this.kursy=listaZam�wie�;
        nazwyKolumn.add("data");
        nazwyKolumn.add("Status zamowienia");
        nazwyKolumn.add("id kontenera");
    //dodawanie do wektora tabeli
        Vector<String> daneDoTabeli1 = new Vector<>();

        for(int i=0;i<kursy.getListaZanowien().size();i++){
            daneDoTabeli1=new Vector<>();
            daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(i).data));
            daneDoTabeli1.add(kursy.getListaZanowien().get(i).status.name());
<<<<<<< Updated upstream
            daneDoTabeli1.add(String.valueOf(kursy.getListaZanowien().get(i).idKontenera));
            if(java.time.LocalDate.now().toString().equals(listaZamówień.getListaZanowien().get(i).getData()))aktualne.add(daneDoTabeli1);
=======
            if(java.time.LocalDate.now().toString().equals(listaZam�wie�.getListaZanowien().get(i).getData()))aktualne.add(daneDoTabeli1);
>>>>>>> Stashed changes
            else
            daneDoTabeli.add(daneDoTabeli1);

        }
        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0,0, 600, 600);
        tabela.setEnabled(false);
        tabela.setVisible(true);

    }


    private JTable tabela;
    private TableModel modelTabeli;
    private Vector<Vector<String>> daneDoTabeli = new Vector<>();
    private Vector<String> nazwyKolumn = new Vector<>();
    private Controllers.ListaZam�wie� kursy = new ListaZam�wie�();
    Vector<Vector<String>> aktualne=new Vector<>();
    public JTable getTabela(){
        return this.tabela;
    }
    public Vector<Vector<String>> getAktualne(){
        return aktualne;
    }
}