package View;

import Controllers.KlasaUzytkownikow;
import Controllers.ListaKontenerow;
import Controllers.ListaZamowien;
import DB.dataBase;
import DB.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

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


        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(800, 600);
        okno.setResizable(false); //blokowanie zmiany rozmiaru okna dla wartosci false
        okno.setLayout(new BorderLayout());
        WidokUzytkownika = new UzytkownikWidok(listaKontenerow);
        oknoOdUzytkownika = WidokUzytkownika.getPanelUzytkownikaCaly();
        okno.getContentPane().add(oknoOdUzytkownika, BorderLayout.NORTH);
        okno.setVisible(true);

        KlasaUzytkownikow xd = new KlasaUzytkownikow("xd", "xd", 2);
        //akcja logowania
        WidokUzytkownika.getButtonZaloguj().addActionListener((ActionEvent e) -> {
            //test kontenerow
//            Map<String, String> data = new HashMap<>();
//            data.put("bruher", "xdxdxd");
//            data.put("crng", "crng");
//            data.put("xdxddxd", "123123");
//            data.put("kaczynski", "crng");
//            db.table("containers").add(data);

            //db.table("containers").delete("apJEuDv3OxQKDR6PoO0w")
            //test:test
            //macias:xdxd
            //bruher:cringo

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
                    alert(db.auth().userName + " zalogowaned", 1);
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
                    alert("nie ma takiego usera xdds", 0);
                    break;
                case BAD_PASSWORD:
                    alert("zle haslo dsxddxd dzbanie", 0);
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
            //tutaj trzeba zrobic wyswietlanie dialoga i odczyta? date kiedy dostarczyc kontener oraz adres.
            //nastepnie zrobic dodanie do bazy danych, najlepiej poprzez wywolanie jakiejs metody w liscie zamowien.
            //potem oczywiscie zaktualizowa? tabele. metoda dawajaca wolne id zamowienia jest zrobiona.
        });
    }

    private void initDB() {
        this.db = new dataBase();
    }

    private void alert(String message, int type) {
        JFrame f = new JFrame("Parent");
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(f, message, "error", type);
    }

    public void register(String name, String pass) {
        Map<String, String> data = new HashMap<>();
        data.put("pass", pass);

        db.auth().register(name, data);
    }

}
