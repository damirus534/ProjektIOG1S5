package View;

import DB.DB;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Okno extends JFrame {

    //metoda ktora inicjuje okno
    public Okno(){
        initComponents();
        initDB();
    }

    JFrame okno = new JFrame();
    private UzytkownikWidok WidokUzytkownika =  new UzytkownikWidok();
    private JPanel oknoOdUzytkownika = WidokUzytkownika.getPanelUzytkownikaCaly();

    private KierowcaWidok WidokKierowcy = new KierowcaWidok();
    private JPanel oknoOdKierowcy = WidokKierowcy.getOknoKierowcy();

    private WlascicielWidok WidokWlasciciela = new WlascicielWidok();
    private JPanel oknoOdWlasciciela = WidokWlasciciela.getPanelWlascicielaCaly();

    private DB db;


    private void initComponents(){
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(800,600);
        okno.setResizable(false); //blokowanie zmiany rozmiaru okna dla wartosci false
        okno.setLayout(new BorderLayout());
        okno.getContentPane().add(oknoOdUzytkownika, BorderLayout.NORTH);
        okno.setVisible(true);

        //akcja logowania
        WidokUzytkownika.getButtonZaloguj().addActionListener((var e) -> {
            //test kontenerow
//            Map<String, String> data = new HashMap<>();
//            data.put("bruher", "xdxdxd");
//            data.put("crng", "crng");
//            data.put("xdxddxd", "123123");
//            data.put("kaczynski", "crng");
//            db.table("containers").add(data);

            System.out.println(db.table("containers").list().toString());
            //db.table("containers").delete("apJEuDv3OxQKDR6PoO0w")

            //test:test
            //macias:xdxd
            //bruher:cringo
            switch (db.auth().login(WidokUzytkownika.getTextFieldLogin().getText(), WidokUzytkownika.getTextFieldHaslo().getText())) {
                case GOOD:
                    alert(db.auth().userName + " zalogowaned");

                    okno.getContentPane().removeAll();
                    okno.getContentPane().invalidate();
                    okno.getContentPane().add(oknoOdKierowcy);
                    okno.getContentPane().revalidate();
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
    }


    private void initDB(){
        this.db = new DB();
    }

    private void alert(String message){
        JFrame f = new JFrame("Parent");
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(f, message, "error", JOptionPane.ERROR_MESSAGE);
    }

    public void register(String name, String pass){
        Map<String, String> data = new HashMap<>();
        data.put("pass", pass);

        db.auth().register(name, data);
    }

}
