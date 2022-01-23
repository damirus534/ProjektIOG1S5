package View;

import Controllers.ListaKontenerow;
import Controllers.ListaZamowien;
import Controllers.Zamowienie;
import DB.dataBase;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Vector;
import static javax.swing.JOptionPane.showMessageDialog;


public class WlascicielWidok extends JPanel {
    //konstruktor
    public WlascicielWidok() {
        initComponents();
    }
    public WlascicielWidok(ListaKontenerow listaKontenerow, ListaZamowien listaZamowien){
        //uzycie danych z bazy danych
        tabelaKursow=new TabelaKursow(listaZamowien);
        tabelaKontenerow=new TabelaKontenerow(listaKontenerow);
        aktualneWektor=tabelaKursow.getAktualne();
        initComponents();
        this.listaZamowien=listaZamowien;
    }



    //zmienne paneli
    private boolean actualView=true;
    private JPanel panelWlascicielaCaly = new JPanel();
    private JPanel panelTabeliKursow = new JPanel();
    private JPanel panelTabeliKontenerow = new JPanel();
    private JPanel panelZarzadzaniaKursami = new JPanel();
    private JPanel panelZarzadzaniaKontenerami = new JPanel();

    //zmienne komponentow
    private JButton zmianaWidokuButton = new JButton("zmien widok");
    private JButton wylogujButton = new JButton("wyloguj");
    private JLabel wlascicielLabel = new JLabel("zalogowany jako: wlasciciel");

    //zmienna dla tabeli kontenerow
    private TabelaKontenerow tabelaKontenerow = new TabelaKontenerow();
    private TabelaKursow tabelaKursow = new TabelaKursow();

    //zmienne dla tabeli kursow
    //wektory
    private Vector<String> KolumnyWektor= new Vector<String>(3);
    private Vector<Vector<String>> KursyWektor= new Vector<Vector<String>>();
    private Vector<Vector<String>> aktualneWektor=new Vector<>();
    //tabele
    private JTable TabelaKursow;
    private JTable AktualnyKurs=new JTable();
    //labele
    private JLabel AktualnyKursLabel=new JLabel();
    private JLabel Kursy=new JLabel();


    //zmienne dla panelu zarzadzania kontenerami
    private JButton dodajKontenerButton = new JButton("Dodaj kontener");
    private JButton usunKontenerButton = new JButton("Usun kontener");

    //zmienne dla panelu zarzadzania kursami
    private JButton zmienKolejnoscButton = new JButton("zmien kolejnosc");

    private ListaZamowien listaZamowien;
    private dataBase db = new dataBase();



    public void initComponents(){
        panelWlascicielaCaly.setPreferredSize(new Dimension(800,600));
        panelWlascicielaCaly.setLayout(new BorderLayout());

        panelTabeliKontenerow.setPreferredSize(new Dimension(600,600));
        panelTabeliKontenerow.setLayout(new BorderLayout());
        panelTabeliKontenerow.add(tabelaKontenerow.getTabela(), BorderLayout.CENTER);
        panelTabeliKontenerow.add(tabelaKontenerow.getTabela().getTableHeader(), BorderLayout.NORTH);
        //trzeba zaimplementowac tabele kursow w osobnej klasie
        //nastepnie przypisac tak jak ta tabele, pozniej w klasie okno zmieniac te panele


        //panel z kursami
        Kursy.setText("Kursy");
        AktualnyKursLabel.setText("Aktualny Kurs");

        Kursy.setFont(new Font("Serif",Font.PLAIN,20));
        AktualnyKursLabel.setFont(new Font("Serif",Font.PLAIN,20));
        //tabela z kursami
        KolumnyWektor.add("Adres");
        KolumnyWektor.add("Numer Kontenera");
        KolumnyWektor.add("Data rozpoczecia kursu");
        TabelaKursow=tabelaKursow.getTabela();

        panelTabeliKursow.setPreferredSize(new Dimension(600,600));
        TabelaKursow.setFillsViewportHeight(true);
        panelTabeliKursow.setLayout(new BoxLayout(panelTabeliKursow,BoxLayout.Y_AXIS));
        TabelaKursow.setFillsViewportHeight(true);
        AktualnyKurs=new JTable(aktualneWektor,KolumnyWektor);
        AktualnyKurs.setBackground(Color.YELLOW);
        AktualnyKurs.setFillsViewportHeight(true);

        panelTabeliKursow.setBorder(new EmptyBorder(new Insets(0,0,50,0)));
        panelTabeliKursow.add(AktualnyKursLabel,BorderLayout.CENTER);
        panelTabeliKursow.add(Box.createRigidArea(new Dimension(0,20)));
        panelTabeliKursow.add(AktualnyKurs.getTableHeader());
        panelTabeliKursow.add(AktualnyKurs);
        panelTabeliKursow.add(Box.createRigidArea(new Dimension(0,50)));
        panelTabeliKursow.add(Kursy);
        panelTabeliKursow.add(Box.createRigidArea(new Dimension(0,20)));
        panelTabeliKursow.add(TabelaKursow.getTableHeader());
        panelTabeliKursow.add(TabelaKursow);
        panelTabeliKursow.setVisible(false);


        //ustawienie panelu bocznego dla kontenerow
        panelZarzadzaniaKontenerami.setPreferredSize(new Dimension(180, 600));
        panelZarzadzaniaKontenerami.setLayout(null);
        panelZarzadzaniaKontenerami.add(wlascicielLabel);
        panelZarzadzaniaKontenerami.add(wylogujButton);
        panelZarzadzaniaKontenerami.add(dodajKontenerButton);
        panelZarzadzaniaKontenerami.add(usunKontenerButton);
        panelZarzadzaniaKontenerami.add(zmianaWidokuButton);

        //ustawienie panelu bocznego dla kursow
        panelZarzadzaniaKursami.setPreferredSize(new Dimension(180, 600));
        panelZarzadzaniaKursami.setLayout(null);
        panelZarzadzaniaKursami.add(zmienKolejnoscButton);
        panelZarzadzaniaKursami.setVisible(false);

        //przypisanie komponentom rozmiarow itp.
        wlascicielLabel.setBounds(10,0,160,20);
        wylogujButton.setBounds(10, 520, 160,20);
        dodajKontenerButton.setBounds(10, 60, 160,20);
        usunKontenerButton.setBounds(10,90,160,20);
        zmianaWidokuButton.setBounds(10, 150, 160,20);
        zmienKolejnoscButton.setBounds(10, 60, 160, 20);

        //zmiana widoku po przycisnieciu przycisku
        zmianaWidokuButton.addActionListener((var e) -> {
            if(actualView){
                actualView = false;
                panelZarzadzaniaKontenerami.setVisible(false);
                panelTabeliKontenerow.setVisible(false);

                panelZarzadzaniaKursami.add(wlascicielLabel);
                panelZarzadzaniaKursami.add(zmianaWidokuButton);
                panelZarzadzaniaKursami.add(wylogujButton);
                panelZarzadzaniaKursami.setVisible(true);
                panelWlascicielaCaly.add(panelZarzadzaniaKursami, BorderLayout.EAST);

                panelTabeliKursow.setVisible(true);
                panelWlascicielaCaly.add(panelTabeliKursow, BorderLayout.WEST);
            }
            else{
                actualView = true;
                panelZarzadzaniaKursami.setVisible(false);
                panelTabeliKursow.setVisible(false);
                panelZarzadzaniaKontenerami.add(wlascicielLabel);
                panelZarzadzaniaKontenerami.add(zmianaWidokuButton);
                panelZarzadzaniaKontenerami.add(wylogujButton);
                panelZarzadzaniaKontenerami.setVisible(true);

                panelTabeliKontenerow.setVisible(true);
            }
        });
        zmienKolejnoscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] option = {
                        "Przesun na poczatek",
                        "Przesun na koniec",
                        "Przesun o jedno w gore",
                        "Przesun o jedno w dol"
                };
                int n = JOptionPane.showOptionDialog(null, "Wybierz opcje", "Wybor",
                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[2]);
                LocalDate data = java.time.LocalDate.now();
                String statusNowy;
                int nrZamowienia=0;
                int wybranyKurs = AktualnyKurs.getSelectedRow();
                if (AktualnyKurs.getSelectedRow() != -1) {
                    nrZamowienia = listaZamowien.znajdzZamowienie(aktualneWektor.get(wybranyKurs).get(0),
                            Integer.parseInt(aktualneWektor.get(wybranyKurs).get(1)));


                switch (n){
                    case 0:if (AktualnyKurs.getSelectedRow()==0){
                        JOptionPane.showMessageDialog(null,"Niepoprawna opercja");
                        break;
                    } else{


                        zmianaKolejnosc(1,listaZamowien.getZamowienie(nrZamowienia-1));
                        zmianaKolejnosc(nrZamowienia,listaZamowien.getZamowienie(0));
                        listaZamowien.zmienKolejnosc(0,nrZamowienia-1);
                        ((DefaultTableModel) AktualnyKurs.getModel()).moveRow(AktualnyKurs.getSelectedRow(),AktualnyKurs.getSelectedRow(),0);
                        ((DefaultTableModel) AktualnyKurs.getModel()).moveRow(1,1,wybranyKurs);
                    }break;
                    case 1:
                        if(AktualnyKurs.getSelectedRow()<aktualneWektor.size()-1) {
                            int size=listaZamowien.getLenght()-1;

                            zmianaKolejnosc(nrZamowienia, listaZamowien.getZamowienie(size));
                            zmianaKolejnosc(size + 1, listaZamowien.getZamowienie(nrZamowienia - 1));
                            listaZamowien.zmienKolejnosc(size, nrZamowienia - 1);
                            ((DefaultTableModel) AktualnyKurs.getModel()).moveRow(wybranyKurs,wybranyKurs,aktualneWektor.size()-1);
                            ((DefaultTableModel) AktualnyKurs.getModel()).moveRow(aktualneWektor.size()-2,aktualneWektor.size()-2,wybranyKurs);
                        }
                        else JOptionPane.showMessageDialog(null,"Niepoprawna opercja");
                        break;
                    case 2:
                        if(AktualnyKurs.getSelectedRow()!=0) {

                            zmianaKolejnosc(nrZamowienia, listaZamowien.getZamowienie(nrZamowienia-2));
                            zmianaKolejnosc(nrZamowienia-1, listaZamowien.getZamowienie(nrZamowienia - 1));
                            listaZamowien.zmienKolejnosc(nrZamowienia-1, nrZamowienia - 2);
                            ((DefaultTableModel) AktualnyKurs.getModel()).moveRow(AktualnyKurs.getSelectedRow(),AktualnyKurs.getSelectedRow(),AktualnyKurs.getSelectedRow()-1);
                        }else JOptionPane.showMessageDialog(null,"Niepoprawna opercja");
                        break;
                    case 3:
                        if(AktualnyKurs.getSelectedRow()<aktualneWektor.size()-1) {

                            zmianaKolejnosc(nrZamowienia+1, listaZamowien.getZamowienie(nrZamowienia - 1));
                            zmianaKolejnosc(nrZamowienia, listaZamowien.getZamowienie(nrZamowienia ));
                            listaZamowien.zmienKolejnosc(nrZamowienia - 1, nrZamowienia );
                            ((DefaultTableModel) AktualnyKurs.getModel()).moveRow(AktualnyKurs.getSelectedRow(),AktualnyKurs.getSelectedRow(),AktualnyKurs.getSelectedRow()+    1);
                        }
                        else JOptionPane.showMessageDialog(null,"Niepoprawna opercja");
                        break;
                }
            }else JOptionPane.showMessageDialog(null,"Niewybrano kolumny");
            }
        });
        dodajKontenerButton.addActionListener((var e) -> {
            tabelaKontenerow.dodawanieKonteneru();
            panelTabeliKontenerow.add(tabelaKontenerow.getTabela(), BorderLayout.CENTER);
            panelTabeliKontenerow.validate();
        });

        usunKontenerButton.addActionListener((var e) -> {
            tabelaKontenerow.usuwanieKonteneru();
            panelTabeliKontenerow.add(tabelaKontenerow.getTabela(), BorderLayout.CENTER);
            panelTabeliKontenerow.validate();
        });

        panelWlascicielaCaly.add(panelZarzadzaniaKontenerami, BorderLayout.EAST);
        panelWlascicielaCaly.add(panelTabeliKontenerow, BorderLayout.WEST);
        panelWlascicielaCaly.setVisible(true);

    }

    //gettery
    public JButton getZmianaWidokuButton() {
        return zmianaWidokuButton;
    }
    public JButton getWylogujButton(){return wylogujButton;}
    public JPanel getPanelWlascicielaCaly() {
        return panelWlascicielaCaly;
    }
    private void zmianaKolejnosc(int start, Zamowienie zamowienie){

        db.table("orders").edit(String.valueOf(start), "adres", zamowienie.getAdres());
        db.table("orders").edit(String.valueOf(start), "containerID", zamowienie.getIdKontenera());
        db.table("orders").edit(String.valueOf(start), "data", zamowienie.getData());
        db.table("orders").edit(String.valueOf(start), "orderID", zamowienie.getIdZamowienia());
        db.table("orders").edit(String.valueOf(start), "status", zamowienie.getStatus());
        db.table("orders").edit(String.valueOf(start), "username", zamowienie.getLoginUzytkownika());

    }

    public ListaZamowien getListaZamowien() {
        return listaZamowien;
    }

    public void setTabelaKursow(TabelaKursow tabelaKursow) {
        this.tabelaKursow = tabelaKursow;
    }

    public void setListaZamowien(ListaZamowien listaZamowien) {
        this.listaZamowien = listaZamowien;
    }
}