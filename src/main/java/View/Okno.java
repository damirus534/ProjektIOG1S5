package View;

<<<<<<< Updated upstream
import DB.DB;
=======
import Controllers.KlasaUzytkownikow;
import Controllers.ListaKontenerow;
import Controllers.ListaZamówieñ;
import Controllers.Zamowienie;
import DB.DataBase;
import DB.Table;
>>>>>>> Stashed changes

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Okno extends JFrame {

    //metoda ktora inicjuje okno
    public Okno() {
        initComponents();
<<<<<<< Updated upstream
        initDB();
=======

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    private DB db;

    private void initComponents() {
=======
    private DataBase db;
    private Table table;

    //lista kontenerow
    private ListaKontenerow listaKontenerow;
    //lista kursow
    private ListaZamówieñ listaZamówieñ;

    private void initComponents() {
        listaKontenerow = new ListaKontenerow(db.table("containers").list());
        listaZamówieñ = new ListaZamówieñ(db.table("orders").list());
>>>>>>> Stashed changes
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(800, 600);
        okno.setResizable(false); //blokowanie zmiany rozmiaru okna dla wartosci false
        okno.setLayout(new BorderLayout());
<<<<<<< Updated upstream
        okno.getContentPane().add(oknoOdUzytkownika, BorderLayout.NORTH);
        okno.setVisible(true);

=======
        WidokUzytkownika = new UzytkownikWidok(listaKontenerow);
        oknoOdUzytkownika = WidokUzytkownika.getPanelUzytkownikaCaly();
        okno.getContentPane().add(oknoOdUzytkownika, BorderLayout.NORTH);
        okno.setVisible(true);

        KlasaUzytkownikow xd = new KlasaUzytkownikow("xd", "xd", 2);
>>>>>>> Stashed changes
        //akcja logowania
        WidokUzytkownika.getButtonZaloguj().addActionListener((var e) -> {
            //test kontenerow
//            Map<String, String> data = new HashMap<>();
//            data.put("bruher", "xdxdxd");
//            data.put("crng", "crng");
//            data.put("xdxddxd", "123123");
//            data.put("kaczynski", "crng");
//            db.table("containers").add(data);

<<<<<<< Updated upstream
            System.out.println(db.table("containers").list().toString());
=======
>>>>>>> Stashed changes
            //db.table("containers").delete("apJEuDv3OxQKDR6PoO0w")
            //test:test
            //macias:xdxd
            //bruher:cringo
            switch (db.auth().login(WidokUzytkownika.getTextFieldLogin().getText(), WidokUzytkownika.getTextFieldHaslo().getText())) {
                case GOOD:
                    String loggedUser = db.auth().userName + "";
                    alert(db.auth().userName + " zalogowaned");
<<<<<<< Updated upstream

                    okno.getContentPane().removeAll();
                    okno.getContentPane().invalidate();
                    okno.getContentPane().add(oknoOdKierowcy);
                    okno.getContentPane().revalidate();
=======
                    switch (db.auth().authStatusGetter()) {
                        case CLIENT:
                            //uzycie nowego konstruktora
                            okno.getContentPane().invalidate();
                            WidokKlienta = new KlientWidok(loggedUser);
                            oknoOdKlienta = WidokKlienta.getOknoKlienta();
                            okno.setContentPane(oknoOdKlienta);
                            okno.getContentPane().revalidate();
                            break;
                        case DRIVER:
                            //uzycie nowego konstrukotra
                            WidokKierowcy = new KierowcaWidok(listaZamówieñ);
                            oknoOdKierowcy = WidokKierowcy.getOknoKierowcy();
                            okno.getContentPane().invalidate();
                            okno.setContentPane(oknoOdKierowcy);
                            okno.getContentPane().revalidate();
                            break;
                        case OWNER:
                            WidokWlasciciela = new WlascicielWidok(listaKontenerow, listaZamówieñ);
                            oknoOdWlasciciela = WidokWlasciciela.getPanelWlascicielaCaly();
                            okno.getContentPane().invalidate();
                            okno.setContentPane(oknoOdWlasciciela);
                            okno.getContentPane().revalidate();

                    }

>>>>>>> Stashed changes
                    break;
                case USER_NULL:
                    alert("nie ma takiego usera xdds");
                    break;
                case BAD_PASSWORD:
                    alert("zle haslo dsxddxd");
                    break;
            }
        });
        //przejscie na okno wlasciciela
        WidokKierowcy.getWylogujButton().addActionListener((var e) -> {
            okno.getContentPane().removeAll();
            okno.getContentPane().invalidate();
            okno.getContentPane().add(oknoOdWlasciciela);
            okno.getContentPane().revalidate();
        });
        WidokWlasciciela.getPrzyciskZmianyWidoku().addActionListener((var e) -> {
            //trzeba dodac tabele kursow do klasy tak jak tabela kontenerow jest dodana i tutaj zmieniac panele po wcisnieciu przycisku
            okno.getContentPane().removeAll();
            okno.getContentPane().invalidate();
            okno.getContentPane().add(oknoOdWlasciciela);
            okno.getContentPane().revalidate();
        });
        WidokKlienta.getWylogujButton().addActionListener((var e) -> {
            okno.getContentPane().removeAll();
            okno.getContentPane().invalidate();
            okno.getContentPane().add(oknoOdWlasciciela);
            okno.getContentPane().revalidate();
        });
<<<<<<< Updated upstream
=======
        
        WidokKlienta.getZlozZamowienieButton().addActionListener((var e) -> {
            //tutaj trzeba zrobic wyswietlanie dialoga i odczytaæ date kiedy dostarczyc kontener oraz adres.
            //nastepnie zrobic dodanie do bazy danych, najlepiej poprzez wywolanie jakiejs metody w liscie zamowien.
            //potem oczywiscie zaktualizowaæ tabele. metoda dawajaca wolne id zamowienia jest zrobiona.
        });
>>>>>>> Stashed changes
    }

    private void initDB() {
        this.db = new DataBase();
    }

    private void alert(String message) {
        JFrame f = new JFrame("Parent");
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(f, message, "error", JOptionPane.ERROR_MESSAGE);
    }

    public void register(String name, String pass) {
        Map<String, String> data = new HashMap<>();
        data.put("pass", pass);

        db.auth().register(name, data);
    }

}
