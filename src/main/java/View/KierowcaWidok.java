package View;

import Controllers.Kontener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class KierowcaWidok extends JFrame {
    //atrybuty okna
    JFrame OknoKierowcy=new JFrame("Kierwoca");
    //tabele
    private JTable TabelaKursow;
    private JTable AktualnyKurs=new JTable();
    //panele
    private JPanel PanelKontenrow=new JPanel();
    private JPanel PanelButton=new JPanel();
    private JScrollPane ScrollbarKontenerow=new JScrollPane();
    //komponety
    private JButton KoniecKursu=new JButton();
    private JButton Wyloguj=new JButton();
    private JButton ZacznijKurs=new JButton();
    private JLabel AktualnyKursLabel=new JLabel();
    private JLabel Kursy=new JLabel();
    //wektory
    private Vector<String> KolumnyWektor;
    private Vector<Vector<String>> KursyWektor;
    //tworzenie okna
    public KierowcaWidok(){
        KursyWektor=new Vector<Vector<String>>();
        KolumnyWektor=new Vector<>(3);
        initComponens();
    }
    //inicjalizacja komponentów
    private void initComponens(){
        //ustawienia okna
        OknoKierowcy.setResizable(false);
        OknoKierowcy.setSize(800,800);
        OknoKierowcy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OknoKierowcy.setLayout(new BorderLayout(10,0));
        //ustawiania panela kotenero
        dodanieKolumn();
        TabelaKursow=new JTable(KursyWektor,KolumnyWektor);
        PanelKontenrow.setPreferredSize(new Dimension(600,800));
        TabelaKursow.setFillsViewportHeight(true);
        PanelKontenrow.setLayout(new BoxLayout(PanelKontenrow,BoxLayout.Y_AXIS));
        TabelaKursow.setFillsViewportHeight(true);
        AktualnyKurs=new JTable(KursyWektor,KolumnyWektor);
        AktualnyKurs.setFillsViewportHeight(true);

        //Dodawanie elementow do panelu z tabelami
        Kursy.setText("Kursy");
        AktualnyKursLabel.setText("Aktualny Kurs");
        Kursy.setFont(new Font("Serif",Font.PLAIN,20));
        AktualnyKursLabel.setFont(new Font("Serif",Font.PLAIN,20));
        PanelKontenrow.setBorder(new EmptyBorder(new Insets(50,0,50,0)));
        PanelKontenrow.add(AktualnyKursLabel,BorderLayout.CENTER);
        PanelKontenrow.add(Box.createRigidArea(new Dimension(0,20)));
        PanelKontenrow.add(AktualnyKurs.getTableHeader());
        PanelKontenrow.add(AktualnyKurs);
        PanelKontenrow.add(Box.createRigidArea(new Dimension(0,50)));
        PanelKontenrow.add(Kursy);
        PanelKontenrow.add(Box.createRigidArea(new Dimension(0,20)));
        PanelKontenrow.add(TabelaKursow.getTableHeader());
        PanelKontenrow.add(TabelaKursow);
        PanelKontenrow.setVisible(true);


        //Ustawianie panelu z buttonami
        PanelButton.setPreferredSize(new Dimension(200,800));
        PanelButton.setLayout(null);
        Wyloguj.setText("Wyloguj");
        Wyloguj.setBounds(50 ,700,100,40);
        KoniecKursu.setText("Koniec Kursu");
        KoniecKursu.setBounds(50,150,100,40);
        ZacznijKurs.setText("Zacznij Kurs");
        ZacznijKurs.setBounds(50,300,100,40);
        PanelButton.add(Wyloguj);
        PanelButton.add(ZacznijKurs);
        PanelButton.add(KoniecKursu);
        //Dodanie paneli do okana
        OknoKierowcy.add(PanelKontenrow,BorderLayout.WEST);
        OknoKierowcy.add(PanelButton,BorderLayout.EAST);








        OknoKierowcy.setVisible(true);
    }
    //Funckja dodajaca do wektora columny
    private void dodanieKolumn(){
        KolumnyWektor.add("Data");
        KolumnyWektor.add("Numer Kontenera");
        KolumnyWektor.add("Data rozpoczecia kursu");
    }

}
