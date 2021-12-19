package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public final class KlientWidok extends JPanel {

    //konstruktor
    public KlientWidok() {
        initComponents();
    }
    

    //zmienne paneli
    private final JPanel panelKlientaCaly = new JPanel();
    private final JPanel panelListy = new JPanel();
    private final JPanel panelListyZamowien = new JPanel();
    private final JPanel panelZamowienia = new JPanel();

    //tabele
    private TabelaKontenerow tabelaKontenerow = new TabelaKontenerow();
    private JTable tabelaZamowien;
    private TableModel modelTabelaZamowien;

    //komponety
    private final JButton aktualneZamowieniaButton = new JButton();
    private final JButton wylogujButton = new JButton();
    private JLabel nazwaUzytkownikaLabel = new JLabel();
    private JButton zlozZamowienieButton = new JButton();

    //wektory
    private Vector<String> KolumnyZamowienWektor;
    private Vector<Vector<String>> ZamowieniaWektor;
    
    //okno z powiadomieniami
    private JTextArea powiadomienie = new JTextArea(20, 20);

    //gettery
    public JPanel getOknoKlienta() {
        return panelKlientaCaly;
    }

    public JButton getWylogujButton() {
        return wylogujButton;
    }

    public void initComponents() {
        //tworzenie okna klienta
        panelKlientaCaly.setSize(800, 600);
        panelKlientaCaly.setLayout(new BorderLayout(0,1));
        
        
        //stworzenie panelu z lista kontenerow
        panelListy.setPreferredSize(new Dimension(600, 600));
        panelListy.setLayout(new BorderLayout(0,0));
        panelListy.setBounds(0, 0, 580, 600);
        
        //stworzenie panelu z lista zamowien
        panelListyZamowien.setPreferredSize(new Dimension(600, 600));
        panelListyZamowien.setLayout(new BorderLayout(0,0));
        
        //tworzenie tabeli z aktualnymi zamowieniami

        KolumnyZamowienWektor = new Vector<String>(2);
        KolumnyZamowienWektor.add("status");
        KolumnyZamowienWektor.add("id zamowienia");
        Vector<String>daneDotabeli = new Vector<String>(2);
        daneDotabeli.add("dostarczono");
        daneDotabeli.add("1");
        ZamowieniaWektor = new Vector<Vector<String>>();
        ZamowieniaWektor.add(daneDotabeli);
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
        panelZamowienia.setPreferredSize(new Dimension(180, 600));
        panelZamowienia.setLayout(null);

        //utworzenie przycisku zaloz zamowienie
        zlozZamowienieButton.setText("Zloz zamowienie");
        zlozZamowienieButton.setBounds(10, 60, 160, 20);
        
        //utworzenie przycisku wyloguj
        wylogujButton.setText("Wyloguj");
        wylogujButton.setBounds(10, 520, 160, 20);
        
        //utworzenie przycisku wyswietl zamowienia
        aktualneZamowieniaButton.setText("Wyswietl zamowienia");
        aktualneZamowieniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(aktualneZamowieniaButton.getText()=="Wyswietl zamowienia"){
                    panelListy.setVisible(false);
                    panelListyZamowien.setVisible(true);
                    panelKlientaCaly.add(panelListyZamowien, BorderLayout.WEST);
                    aktualneZamowieniaButton.setText("Wyswietl kontenery");
                }
                else{
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
        nazwaUzytkownikaLabel.setBounds(10, 0, 160,20);
        nazwaUzytkownikaLabel.setText("zalogowany jako: macias");
        panelZamowienia.add(nazwaUzytkownikaLabel);
        
        //okno z powiadomieniami
        powiadomienie.setText("Aktualizacja dla \ntwojego zamowinia! \nZobacz teraz!");
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
