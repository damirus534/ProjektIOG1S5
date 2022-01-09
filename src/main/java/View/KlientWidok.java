package View;

import DB.DataBase;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public final class KlientWidok extends JPanel {

    //konstruktor
    public KlientWidok(String loggedUser) {
        this.loggedUser = loggedUser;
        initComponents();
    }
    
    public KlientWidok() {
        this.KursyWektor = new Vector<>();
        this.KolumnyWektor = new Vector<>(3);
        initComponents();
    }

    //baza danych
    DataBase db = new DataBase();
    
    //zalogowany aktualnie uzytkownik
    String loggedUser = "";

    //zmienne paneli
    private final JPanel panelKlientaCaly = new JPanel();
    private final JPanel panelListy = new JPanel();
    private final JPanel panelListyZamowien = new JPanel();
    private final JPanel panelZamowienia = new JPanel();

    //tabele
    private final DefaultTableModel tabelaZamowien = new DefaultTableModel();
    private TabelaKontenerow tabelaKontenerow = new TabelaKontenerow();

    //komponety
    private final JButton aktualneZamowieniaButton = new JButton();
    private final JButton wylogujButton = new JButton();
    private final JLabel aktualneZamowienieLabel = new JLabel();

    //wektory
<<<<<<< Updated upstream
    private final Vector<String> KolumnyWektor;
    private final Vector<Vector<String>> KursyWektor;
    
=======
    private Vector<String> KolumnyZamowienWektor;
    private Vector<Vector<String>> ZamowieniaWektor;

>>>>>>> Stashed changes
    //okno z powiadomieniami
    private JTextArea powiadomienie = new JTextArea(20, 20);

    //gettery
    public JPanel getOknoKlienta() {
        return panelKlientaCaly;
    }

    public JButton getWylogujButton() {
        return wylogujButton;
    }
    
    //settery
    public void setLoggedUser(String loggedUser){
        this.loggedUser = loggedUser;
    }

    public void initComponents() {
        //tworzenie okna klienta
        panelKlientaCaly.setSize(800, 600);
<<<<<<< Updated upstream
        panelKlientaCaly.setLayout(new BorderLayout());
        
        
        //stworzenie panelu z lista kontenerow
        panelListy.setPreferredSize(new Dimension(600, 300));
        panelListy.setLayout(new BorderLayout());
        panelListy.setBounds(0, 300, 590, 300);
        
        //stworzenie panelu z lista zamowien
        panelListyZamowien.setPreferredSize(new Dimension(590, 300));
        panelListyZamowien.setLayout(new BorderLayout());
        
        //tworzenie tabeli z aktualnymi zamowieniami
        tabelaZamowien.addColumn("nr_Zamowienia");
        tabelaZamowien.addColumn("Status");
        JTable tabela = new JTable(tabelaZamowien);
        tabela.setVisible(true);
        tabela.setEnabled(false);
        panelListyZamowien.add(new JScrollPane(tabela));
        
=======
        panelKlientaCaly.setLayout(new BorderLayout(0, 1));

        //stworzenie panelu z lista kontenerow
        panelListy.setPreferredSize(new Dimension(600, 600));
        panelListy.setLayout(new BorderLayout(0, 0));
        panelListy.setBounds(0, 0, 580, 600);

        //stworzenie panelu z lista zamowien
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
        

        modelTabelaZamowien = new DefaultTableModel(ZamowieniaWektor, KolumnyZamowienWektor);
        tabelaZamowien = new JTable(modelTabelaZamowien);
        tabelaZamowien.setVisible(true);
        tabelaZamowien.setEnabled(false);
        panelListyZamowien.add(tabelaZamowien.getTableHeader(), BorderLayout.NORTH);
        panelListyZamowien.add(tabelaZamowien, BorderLayout.CENTER);

>>>>>>> Stashed changes
        //dodanie tabeli kontenerow do layoutu
        panelListy.add(tabelaKontenerow.getTabela(), BorderLayout.CENTER);
        panelListy.add(tabelaKontenerow.getTabela().getTableHeader(), BorderLayout.NORTH);

        //Ustawianie panelu z przyciskami
        panelZamowienia.setPreferredSize(new Dimension(200, 600));
        panelZamowienia.setLayout(null);
<<<<<<< Updated upstream
        
        //utworzenie przycisku wyloguj
        wylogujButton.setText("Wyloguj");
        wylogujButton.setBounds(30, 500, 160, 30);
        
=======

        //utworzenie przycisku zaloz zamowienie
        zlozZamowienieButton.setText("Zloz zamowienie");
        zlozZamowienieButton.setBounds(10, 60, 160, 20);

        //utworzenie przycisku wyloguj
        wylogujButton.setText("Wyloguj");
        wylogujButton.setBounds(10, 520, 160, 20);

>>>>>>> Stashed changes
        //utworzenie przycisku wyswietl zamowienia
        aktualneZamowieniaButton.setText("Wyswietl zamowienia");
        aktualneZamowieniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
<<<<<<< Updated upstream
                
                tabelaZamowien.setRowCount(0);
                //tutaj bedzie pobieranie danych z bazy
                String tab[] = {"1", "Kontener zabiera œmieci na wysypisko"};
                tabelaZamowien.addRow(tab);
                
            }
        }
        );
        aktualneZamowieniaButton.setBounds(30, 100, 160, 30);
        
        //dodanie przycikow do panelu
        panelZamowienia.add(wylogujButton);
        panelZamowienia.add(aktualneZamowieniaButton);
        
=======

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

>>>>>>> Stashed changes
        //okno z powiadomieniami
        powiadomienie.setText("Aktualizacja dla \ntwojego zamowinia! \nZobacz teraz!");
        powiadomienie.setVisible(true);
        powiadomienie.setPreferredSize(new Dimension(800, 600));
        powiadomienie.setEditable(false);
        powiadomienie.setBounds(30, 300, 160, 50);
        powiadomienie.doLayout();
        panelZamowienia.add(powiadomienie);

        //dodanie paneli do okna
        panelKlientaCaly.add(panelListy, BorderLayout.WEST);
        panelKlientaCaly.add(panelZamowienia, BorderLayout.EAST);
        panelKlientaCaly.add(panelListyZamowien, BorderLayout.WEST);
    }
}
