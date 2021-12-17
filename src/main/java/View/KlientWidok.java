package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Vector;

public class KlientWidok extends JFrame {

    //metoda ktora inicjuje okno
    public KlientWidok() {
        initComponents();
    }

    //zmienna okna
    JFrame oknoKlienta = new JFrame("Panel Klienta");
    //zmienne dla paneli znajdujacych sie w oknie
    private JPanel panelWylogowania = new JPanel();
    private JPanel panelAktualnychZamowien = new JPanel();
    //zmienne dla PaneluAktualnychZamowien
    private Vector<Vector<String>> daneDoTabeli;
    private Vector<String> nazwyKolumn;
    private TableModel modelTabeli;
    private JTable tabela = new JTable();
    //zmienne do wylogowywania się
    private JButton buttonWyloguj = new JButton();
    private JTextField textFieldWyloguj = new JTextField();

    private void initComponents() {//metoda do edycji

        //tworzenie okna dla uzytkownika z lista kontenerow i logowaniem
        oknoKlienta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oknoKlienta.setSize(800, 600);
        oknoKlienta.setResizable(false); //blokowanie zmiany rozmiaru okna dla wartosci false
        oknoKlienta.setLayout(new BorderLayout(0, 0));

        //tworzenie panelu na ktorym bedzie wyswietlac sie lista aktualnych zamowien
        panelAktualnychZamowien.setPreferredSize(new Dimension(600, 600));
        panelAktualnychZamowien.setLayout(null);
        daneDoTabeli = new Vector<Vector<String>>();
        nazwyKolumn = new Vector<String>();
        nazwyKolumn.add("nr zamowienia");
        nazwyKolumn.add("Status");
        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0, 0, 600, 600);
        tabela.setEnabled(false);
        tabela.setVisible(true);

        //tworzenie panelu w ktorym bedzie mozna sie wylogowac
        buttonWyloguj.setBounds(30, 200, 160, 20);
        buttonWyloguj.setText("wyloguj się");
        panelWylogowania.add(buttonWyloguj);
        panelWylogowania.setVisible(true);


        //dodanie paneli do okna
        oknoKlienta.add(panelAktualnychZamowien, BorderLayout.WEST);
        oknoKlienta.add(panelWylogowania, BorderLayout.EAST);


        oknoKlienta.setVisible(true);

        //nw jak te powiadomienia zrobic no i dzien dostarczenia wolnego kontenera

    }
}
