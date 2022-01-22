package View;

import DB.dataBase;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
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
    private JPanel panelKlientaCaly = new JPanel();
    ;
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
    public void setLoggedUser(String loggedUsr) {
        panelKlientaCaly.removeAll();
        loggedUser = loggedUsr;
        initComponents();
    }

    public void setTabelaKontenerow(TabelaKontenerow tabelaKontenerow) {
        this.tabelaKontenerow = tabelaKontenerow;
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

        ZamowieniaWektor = new Vector<Vector<String>>();
        Vector<String> constainersIds = new Vector<String>();

        Map<String, Object> orders = db.table("orders").list();
        String dataZamowieniaUzytkownika = "";

        //petla ktora sprawdza ktore zamowienia w dazie 
        //naleza do zalogowanego uzytkownika
        for (int i = 1; i <= orders.size(); ++i) {
            String iString = i + "";
            HashMap<String, Object> order = (HashMap<String, Object>) orders.get(iString);
            String username = (String) order.get("username");
            String status = (String) order.get("status");
            String dataZamowienia = (String) order.get("data");
            long idZamowienia = (long) order.get("orderID");
            String idZamowieniaTekst = idZamowienia + "";
            long containerId = (long) order.get("containerID");
            String containerIdText = containerId + "";

            if (loggedUser.equals(username)) {
                constainersIds.add(containerIdText);
                Vector<String> daneDotabeli = new Vector<String>(2);
                daneDotabeli.add(status);
                daneDotabeli.add(idZamowieniaTekst);
                ZamowieniaWektor.add(daneDotabeli);
                if (dataZamowienia.compareTo(dataZamowieniaUzytkownika) > 0) {
                    dataZamowieniaUzytkownika = dataZamowienia;
                }
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Object> conatiners = db.table("containers").list();

        //sprawdzenie czy zamowione kontenery nie sa dostepne
        boolean status = false;
        String containerIdText = "";
        String availability = "";
        long containerId = 0;
        int size = conatiners.size();
        for (int i = 1; i <= size; ++i) {
            try {
                if (i > 100) {
                    break;
                }
                String iString = i + "";
                HashMap<String, Object> container = (HashMap<String, Object>) conatiners.get(iString);
                containerId = (long) container.get("id");
                status = (boolean) container.get("status");
                containerIdText = containerId + "";
                availability = (String) container.get("dostepnosc");
            } catch (Exception e) {
                ++size;
                continue;
            }
            for (String containerID : constainersIds) {
                if (containerID.equals(containerIdText) && status) {
                    try {
                        status = false;
                        Date availabilityDate = sdf.parse(availability);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(availabilityDate);
                        cal.add(Calendar.DATE, 7);
                        availabilityDate = cal.getTime();
                        String formatted = sdf.format(availabilityDate);
                        db.table("containers").edit(String.valueOf(containerId), "status", status);
                        db.table("containers").edit(String.valueOf(containerId), "dostepnosc", formatted);
                    } catch (ParseException ex) {
                        continue;
                    }
                }
            }
        }

        //pobranie aktualnej daty
        String date2 = java.time.LocalDate.now().toString();
        long diff = 0;

        //sprawdzanie czy uzytkownik posiada jakies zamowienia
        if (!ZamowieniaWektor.isEmpty()) {
            try {
                Date firstDate = sdf.parse(dataZamowieniaUzytkownika);
                Date secondDate = sdf.parse(date2);
                long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if (diff < 2) {
                    diff = 2;
                }
            } catch (ParseException ex) {
                diff = 2;
            }
        }
        powiadomienie = new JTextArea(20, 20);
        powiadomienie.setText("Nie masz zadnych \npowiadomien");
        if (!ZamowieniaWektor.isEmpty()) {
            switch (ZamowieniaWektor.get(0).get(0)) {
                case "Zakonczono":
                    powiadomienie.setText("Twoje zamowienie \nzostalo zakonczone.");
                    break;
                case "OczekiwaniaNaDostarczenie":
                    powiadomienie.setText("Twoje zamowienie \noczekuje na dostarczenie\n"
                            + "\nPozostaly " + (diff - 2) + " dni do\ndostarczenia kontenera.");
                    break;
                case "DostarczenieDoKlienta":
                    powiadomienie.setText("Twoje zamowienie \njest dostarczane do ciebie\n"
                            + "\nPozostaly " + (diff - 1) + " dni do\ndostarczenia do\nPana/Pani kontenera.");
                    break;
                case "DostarcznieDoWysypiska":
                    powiadomienie.setText("Twoje zamowienie \njest dostarczane do wysypiska\n"
                            + "\nPozostaly " + diff + " dni do\nzabrania kontenera\nna wysypisko.");
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
