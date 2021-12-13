package View;

import DB.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class UzytkownikWidok extends JFrame{

    //metoda ktora inicjuje okno
    public UzytkownikWidok(){
        initComponents();
        initDB();
    }
    //zmienna okna
    JFrame oknoUzytkownika = new JFrame("Wywozka smieci");
    //zmienne dla paneli znajdujacych sie w oknie
    private JPanel panelLogowania = new JPanel();
    private JPanel panelListy = new JPanel();
    //zmienne dla paneluListy
    private Vector<Vector<String>> daneDoTabeli;
    private Vector<String> nazwyKolumn;
    private TableModel modelTabeli;
    private JTable tabela = new JTable();
    //zmienne dla paneluLogowania
    private JLabel labelLogin= new JLabel();
    private JLabel labelHaslo= new JLabel();
    private JTextField textFieldLogin = new JTextField();
    private JTextField textFieldHaslo = new JTextField();
    private JButton buttonZaloguj = new JButton();

    //db
    private DB db;

    private void initComponents() {//metoda do edycji

        //tworzenie okna dla uzytkownika z lista kontenerow i logowaniem
        oknoUzytkownika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oknoUzytkownika.setSize(800,600);
        oknoUzytkownika.setResizable(false); //blokowanie zmiany rozmiaru okna dla wartosci false
        oknoUzytkownika.setLayout(new BorderLayout(0,0));

        //tworzenie panelu na ktorym bedzie wyswietlac sie lista
        panelListy.setPreferredSize(new Dimension(600,600));
        panelListy.setLayout(null);
        daneDoTabeli = new Vector<Vector<String>>();
        nazwyKolumn = new Vector<String>();
        nazwyKolumn.add("nr Konetenra");
        nazwyKolumn.add("Rozmiar kontenera");
        nazwyKolumn.add("Status");
        modelTabeli = new DefaultTableModel(daneDoTabeli, nazwyKolumn);
        tabela = new JTable(modelTabeli);
        tabela.setBounds(0,0,600,600);
        tabela.setEnabled(false);
        tabela.setVisible(true);
        panelListy.add(tabela);

        //tworzenie panelu w ktorym bedzie mozna sie zalogowac
        panelLogowania.setPreferredSize(new Dimension(200, 600));
        panelLogowania.setLayout(null);
        labelLogin.setText("Login:");
        labelLogin.setBounds(30,50, 160, 20);
        labelHaslo.setText("Haslo:");
        labelHaslo.setBounds(30, 120, 160, 20);
        textFieldLogin.setBounds(30, 80, 160, 20);
        textFieldHaslo.setBounds(30, 150, 160, 20);
        buttonZaloguj.setBounds(30,200, 160, 20);
        buttonZaloguj.setText("zaloguj się");
        panelLogowania.add(labelLogin);
        panelLogowania.add(labelHaslo);
        panelLogowania.add(textFieldLogin);
        panelLogowania.add(textFieldHaslo);
        panelLogowania.add(buttonZaloguj);
        panelLogowania.setVisible(true);

        //akcja logowania
        buttonZaloguj.addActionListener((var e) -> {
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
//            switch (db.auth().login(textFieldLogin.getText(), textFieldHaslo.getText())){
//                case GOOD:
//                    alert(db.auth().userName + " zalogowaned");
//                    break;
//                case USER_NULL:
//                    alert("nie ma takiego usera xdds");
//                    break;
//                case BAD_PASSWORD:
//                    alert("zle haslo dsxddxd");
//                    break;
//            }
        });



        //dodanie paneli do okna
        oknoUzytkownika.add(panelListy, BorderLayout.WEST);
        oknoUzytkownika.add(panelLogowania, BorderLayout.EAST);


        oknoUzytkownika.setVisible(true);

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