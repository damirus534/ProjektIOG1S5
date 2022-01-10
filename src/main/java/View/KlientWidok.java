package View;


import Controllers.StatusZamowienia;
import DB.dataBase;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public final class KlientWidok extends JPanel {

    //konstruktor    
    public KlientWidok() {
        this.loggedUser = "";
    }
    

    //baza danych
    dataBase db = new dataBase();
    
    //zalogowany aktualnie uzytkownik
    private String loggedUser;

    //zmienne paneli
    private JPanel panelKlientaCaly = new JPanel();;
    private JPanel panelListy;
    private JPanel panelListyZamowien;
    private JPanel panelZamowienia;

    //tabele
    private TabelaKontenerow tabelaKontenerow = new TabelaKontenerow();
    private JTable tabelaZamowien;
    private TableModel modelTabelaZamowien;

    //komponety
    private JButton aktualneZamowieniaButton;
    private final JButton wylogujButton = new JButton();
    private JLabel nazwaUzytkownikaLabel = new JLabel();
    private JButton zlozZamowienieButton = new JButton();

    //wektory
    private Vector<String> KolumnyZamowienWektor;
    private Vector<Vector<String>> ZamowieniaWektor;

    //okno z powiadomieniami
    private JTextArea powiadomienie;

    //gettery
    public JPanel getOknoKlienta() {
        return panelKlientaCaly;
    }

    public JButton getZlozZamowienieButton() {
        return zlozZamowienieButton;
    }

    public JButton getWylogujButton() {
        return wylogujButton;
    }
    
    //settery
    public void setLoggedUser(String loggedUsr){
        panelKlientaCaly.removeAll();
        loggedUser = loggedUsr;
        initComponents();
    }

    public void initComponents() {
        //tworzenie okna klienta
        
        panelKlientaCaly.setSize(800, 600);
        panelKlientaCaly.setLayout(new BorderLayout(0, 1));

        //stworzenie panelu z lista kontenerow
        panelListy = new JPanel();
        panelListy.setPreferredSize(new Dimension(600, 600));
        panelListy.setLayout(new BorderLayout(0, 0));
        panelListy.setBounds(0, 0, 580, 600);

        //stworzenie panelu z lista zamowien
        panelListyZamowien = new JPanel();
        panelListyZamowien.setPreferredSize(new Dimension(600, 600));
        panelListyZamowien.setLayout(new BorderLayout(0, 0));

        //tworzenie tabeli z aktualnymi zamowieniami
        KolumnyZamowienWektor = new Vector<String>(2);
        KolumnyZamowienWektor.add("status");
        KolumnyZamowienWektor.add("id zamowienia");
        
        Vector<String> daneDotabeli = new Vector<String>(2);
        Map<String, Object> orders = db.table("orders").list();

        //petla ktora sprawdza ktore zamowienia w dazie 
        //naleza do zalogowanego uzytkownika
        for (int i = 1; i <= orders.size(); ++i) {
            String iString = i + "";
            HashMap<String, Object> order = (HashMap<String, Object>) orders.get(iString);
            String username = (String) order.get("username");
            String status = (String) order.get("status");
            long idZamowienia = (long) order.get("orderID");
            String idZamowieniaTekst = idZamowienia + "";
            if (loggedUser.equals(username)) {
                daneDotabeli.add(status);
                daneDotabeli.add(idZamowieniaTekst);
            }
        }
        
        //sprawdzanie czy uzytkownik posiada jakies zamowienia
        ZamowieniaWektor = new Vector<Vector<String>>();
        if(!daneDotabeli.isEmpty())
        {
            ZamowieniaWektor.add(daneDotabeli);
        }
        powiadomienie = new JTextArea(20, 20);
        powiadomienie.setText("Nie masz zadnych \npowiadomien");
        if(!ZamowieniaWektor.isEmpty())
        {
                switch(ZamowieniaWektor.get(0).get(0))
                {
                    case "Zakonczono":
                        powiadomienie.setText("Twoje zamowienie \nzostalo zakonczone");
                        break;
                    case "OczekiwaniaNaDostarczenie":
                        powiadomienie.setText("Twoje zamowienie \noczekuje na dostarczenie");
                        break;
                    case "DostarczenieDoKlienta":
                        powiadomienie.setText("Twoje zamowienie \njest dostarczane do ciebie");
                        break;
                    case "DostarcznieDoWysypiska":
                        powiadomienie.setText("Twoje zamowienie \njest dostarczane do wysypiska");
                        break;
                    default:
                        powiadomienie.setText("Nie masz zadnych \npowiadomien");
                        break;
                }
        }

        modelTabelaZamowien = new DefaultTableModel(ZamowieniaWektor, KolumnyZamowienWektor);
        tabelaZamowien = new JTable(modelTabelaZamowien);
        tabelaZamowien.setVisible(true);
        tabelaZamowien.setEnabled(false);
        panelListyZamowien.add(tabelaZamowien.getTableHeader(), BorderLayout.NORTH);
        panelListyZamowien.add(tabelaZamowien, BorderLayout.CENTER);

        //dodanie tabeli kontenerow do layoutu
        panelListy.add(tabelaKontenerow.getTabela(), BorderLayout.CENTER);
        panelListy.add(tabelaKontenerow.getTabela().getTableHeader(), BorderLayout.NORTH);

        //Ustawianie panelu z przyciskami
        panelZamowienia = new JPanel();
        panelZamowienia.setPreferredSize(new Dimension(180, 600));
        panelZamowienia.setLayout(null);

        //utworzenie przycisku zaloz zamowienie
        zlozZamowienieButton.setText("Zloz zamowienie");
        zlozZamowienieButton.setBounds(10, 60, 160, 20);

        //utworzenie przycisku wyloguj
        wylogujButton.setText("Wyloguj");
        wylogujButton.setBounds(10, 520, 160, 20);

        //utworzenie przycisku wyswietl zamowienia
        aktualneZamowieniaButton = new JButton();
        aktualneZamowieniaButton.setText("Wyswietl zamowienia");
        aktualneZamowieniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (aktualneZamowieniaButton.getText() == "Wyswietl zamowienia") {
                    panelListy.setVisible(false);
                    panelListyZamowien.setVisible(true);
                    panelKlientaCaly.add(panelListyZamowien, BorderLayout.WEST);
                    aktualneZamowieniaButton.setText("Wyswietl kontenery");
                } else {
                    panelListyZamowien.setVisible(false);
                    panelListy.setVisible(true);
                    panelKlientaCaly.add(panelListy, BorderLayout.WEST);
                    aktualneZamowieniaButton.setText("Wyswietl zamowienia");
                }
            }
        }
        );
        aktualneZamowieniaButton.setBounds(10, 100, 160, 20);

        //dodanie przycikow do panelu
        panelZamowienia.add(wylogujButton);
        panelZamowienia.add(aktualneZamowieniaButton);
        panelZamowienia.add(zlozZamowienieButton);

        //dodanie labeli do panelu bocznego
        nazwaUzytkownikaLabel.setBounds(10, 0, 160, 20);
        nazwaUzytkownikaLabel.setText("zalogowany jako: " + loggedUser);
        panelZamowienia.add(nazwaUzytkownikaLabel);

        //okno z powiadomieniami
        powiadomienie.setVisible(true);
        powiadomienie.setPreferredSize(new Dimension(800, 600));
        powiadomienie.setEditable(false);
        powiadomienie.setBounds(10, 250, 160, 200);
        powiadomienie.doLayout();
        panelZamowienia.add(powiadomienie);

        //dodanie paneli do okna
        panelKlientaCaly.add(panelListy, BorderLayout.WEST);
        panelKlientaCaly.add(panelZamowienia, BorderLayout.EAST);
    }
}
