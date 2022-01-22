package View;

import Controllers.*;
import DB.dataBase;
import DB.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Okno extends JFrame {

    //metoda ktora inicjuje okno
    public Okno() {
        initDB();
        initComponents();

    }

    JFrame okno = new JFrame();
    private UzytkownikWidok WidokUzytkownika = new UzytkownikWidok();
    private JPanel oknoOdUzytkownika = WidokUzytkownika.getPanelUzytkownikaCaly();

    private KierowcaWidok WidokKierowcy = new KierowcaWidok();
    private JPanel oknoOdKierowcy = WidokKierowcy.getOknoKierowcy();

    private WlascicielWidok WidokWlasciciela = new WlascicielWidok();
    private JPanel oknoOdWlasciciela = WidokWlasciciela.getPanelWlascicielaCaly();

    private KlientWidok WidokKlienta = new KlientWidok();
    private JPanel oknoOdKlienta = WidokKlienta.getOknoKlienta();

    private dataBase db;
    private Table table;

    //lista kontenerow
    private ListaKontenerow listaKontenerow;
    //lista kursow
    private ListaZamowien listaZamowien;

    private void initComponents() {
        listaKontenerow = new ListaKontenerow(db.table("containers").list());
        listaZamowien = new ListaZamowien(db.table("orders").list());

        WidokWlasciciela = new WlascicielWidok(listaKontenerow, listaZamowien);
        oknoOdWlasciciela = WidokWlasciciela.getPanelWlascicielaCaly();
        WidokKierowcy = new KierowcaWidok(listaZamowien);
        oknoOdKierowcy = WidokKierowcy.getOknoKierowcy();
        
        WidokUzytkownika = new UzytkownikWidok (listaKontenerow);
        

        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(800, 600);
        okno.setResizable(false); //blokowanie zmiany rozmiaru okna dla wartosci false
        okno.setLayout(new BorderLayout());
        WidokUzytkownika = new UzytkownikWidok(listaKontenerow);
        oknoOdUzytkownika = WidokUzytkownika.getPanelUzytkownikaCaly();
        okno.getContentPane().add(oknoOdUzytkownika, BorderLayout.NORTH);
        okno.setVisible(true);

        //akcja logowania
        WidokUzytkownika.getButtonZaloguj().addActionListener((ActionEvent e) -> {

            if(WidokUzytkownika.getTextFieldLogin().getText().equals("")) {
                alert("wpisz swoja nazwe", 0);
                return;
            }

            if(WidokUzytkownika.getTextFieldHaslo().getText().equals("")) {
                alert("wpisz swoje haslo", 0);
                return;
            }

            switch (db.auth().login(WidokUzytkownika.getTextFieldLogin().getText(), WidokUzytkownika.getTextFieldHaslo().getText())) {
                case GOOD:
                    String loggedUser = db.auth().userName + "";
                    alert(db.auth().userName + " zalogowano", 1);
                    switch (db.auth().authStatusGetter()) {
                        case CLIENT:
                            //uzycie nowego konstruktora
                            WidokKlienta.setTabelaKontenerow(new TabelaKontenerow(listaKontenerow));
                            WidokKlienta.setLoggedUser(loggedUser);

                            okno.getContentPane().invalidate();
                            okno.setContentPane(oknoOdKlienta);
                            okno.getContentPane().revalidate();

                            
                            break;
                        case DRIVER:

                            okno.getContentPane().invalidate();
                            okno.setContentPane(oknoOdKierowcy);
                            okno.getContentPane().revalidate();
                            break;
                        case OWNER:

                            okno.getContentPane().invalidate();
                            okno.setContentPane(oknoOdWlasciciela);
                            okno.getContentPane().revalidate();

                    }

                    break;
                case USER_NULL:
                    alert("user nie istnieje", 0);
                    break;
                case BAD_PASSWORD:
                    alert("zle haslo", 0);
                    break;
            }
        });
        //przejscie na okno wlasciciela
        WidokKierowcy.getWylogujButton().addActionListener((var e) -> {
            
            okno.getContentPane().invalidate();
            okno.setContentPane(oknoOdUzytkownika);
            okno.getContentPane().revalidate();
        });
        WidokWlasciciela.getZmianaWidokuButton().addActionListener((var e) -> {
            //trzeba dodac tabele kursow do klasy tak jak tabela kontenerow jest dodana i tutaj zmieniac panele po wcisnieciu przycisku
            
            okno.getContentPane().revalidate();
        });
        WidokWlasciciela.getWylogujButton().addActionListener((var e) -> {
            WidokUzytkownika.refreshContainers(listaKontenerow);
            okno.getContentPane().invalidate();
            okno.setContentPane(oknoOdUzytkownika);
            okno.getContentPane().revalidate();
        });

        WidokKlienta.getWylogujButton().addActionListener((var e) -> {
            okno.getContentPane().invalidate();
            okno.setContentPane(oknoOdUzytkownika);
            okno.getContentPane().revalidate();
        });
        
        WidokKlienta.getZlozZamowienieButton().addActionListener((var e) -> {
            String adres = dialogbox();
            int wolne_id = listaZamowien.zwrocWolneIdZamowienia();
            long id_kontenera = listaKontenerow.wolneID();
            String date = listaKontenerow.podajAktualnaDate();
            long pierwszyWolnyKontener = listaKontenerow.zwrocPierwszyWolnyKontener();
            String dostepnosc = listaKontenerow.getLista().get((int)pierwszyWolnyKontener).getNajblizszaDostepnosc();
            if(adres=="")
            {
                JOptionPane.showMessageDialog(okno, "Adres nie może być pusty!", "error", JOptionPane.ERROR_MESSAGE);
            }
            else if(adres.length()<=5)
            {
                JOptionPane.showMessageDialog(okno, "Adres nie może być krótszy niż 5 znaków!", "error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try 
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date2 = java.time.LocalDate.now().toString();
                    Date availabilityDate = sdf.parse(date2);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(availabilityDate);
                    cal.add(Calendar.DATE, 7);
                    availabilityDate = cal.getTime();
                    String formatted = sdf.format(availabilityDate);
                    var nowe_zamowienie = new Zamowienie(wolne_id,db.auth().userName, formatted,adres,listaKontenerow.wolneID(), StatusZamowienia.DostarczenieDoKlienta);
                    listaZamowien.dodajZamowienie(nowe_zamowienie);
                    var nowy_kontener = new Kontener(false,dostepnosc,id_kontenera);
                    listaKontenerow.ListaKontenerowDodajKontener(nowy_kontener);
                    addToContainersDB(formatted,false,id_kontenera);
                    addToOrdersDB(adres,id_kontenera,formatted,wolne_id,StatusZamowienia.DostarczenieDoKlienta.name(),db.auth().userName);
                }
                catch (ParseException ex) 
                {
                    var nowe_zamowienie = new Zamowienie(wolne_id,db.auth().userName, date,adres,listaKontenerow.wolneID(), StatusZamowienia.DostarczenieDoKlienta);
                    listaZamowien.dodajZamowienie(nowe_zamowienie);
                    var nowy_kontener = new Kontener(false,dostepnosc,id_kontenera);
                    listaKontenerow.ListaKontenerowDodajKontener(nowy_kontener);
                    addToContainersDB(dostepnosc,false,id_kontenera);
                    addToOrdersDB(adres,id_kontenera,date,wolne_id,StatusZamowienia.DostarczenieDoKlienta.name(),db.auth().userName);
                }
                    

            }
        });
    }

    private void initDB() {
        this.db = new dataBase();
    }

    private String dialogbox() {

        String adres = JOptionPane.showInputDialog(okno,"Prosze podac adres:");

        int a = JOptionPane.showConfirmDialog(okno, "Czy wpisano poprawny adres?");

        if (a == JOptionPane.YES_OPTION) {okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}
        return adres;
    }
    private void addToContainersDB(String dostepnosc,boolean status,long id)
    {
        dataBase db = new dataBase();

        Map<String, Object> data = new HashMap<>();
        data.put("dostepnosc", dostepnosc);
        data.put("status", status);
        data.put("id", id);

        db.table("containers").add(String.valueOf(id), data);
    }
    private void addToOrdersDB(String adres,long idContainer,String date,int idOrder,String status,String username)
    {
        dataBase db = new dataBase();

        Map<String, Object> data = new HashMap<>();
        data.put("adres", adres);
        data.put("containerID", idContainer);
        data.put("data", date);
        data.put("orderID",idOrder);
        data.put("status",status);
        data.put("username",username);

        db.table("orders").add(String.valueOf(idOrder), data);
    }
    private void alert(String message, int type) {
        JFrame f = new JFrame("Parent");
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(f, message, "error", type);
    }

}
