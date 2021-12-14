package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public final class KlientWidok extends JPanel {

    //konstruktor
    public KlientWidok() {
        this.KursyWektor = new Vector<>();
        this.KolumnyWektor = new Vector<>(3);
        initComponents();
    }

    //zmienne paneli
    private final JPanel panelKlientaCaly = new JPanel();
    private final JPanel panelListy = new JPanel();
    private final JPanel panelZamowienia = new JPanel();

    //tabele
    private final DefaultTableModel tabelaZamowien = new DefaultTableModel();

    //komponety
    private final JButton aktualneZamowieniaButton = new JButton();
    private final JButton wylogujButton = new JButton();
    private final JLabel aktualneZamowienieLabel = new JLabel();

    //wektory
    private final Vector<String> KolumnyWektor;
    private final Vector<Vector<String>> KursyWektor;
    
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
        panelKlientaCaly.setLayout(new BorderLayout());
        panelListy.setPreferredSize(new Dimension(600, 600));
        panelListy.setLayout(new BoxLayout(panelListy, BoxLayout.Y_AXIS));
        
        
        //stworzenie panelu z lista zamowien
        panelListy.setPreferredSize(new Dimension(600, 600));
        panelListy.setLayout(new BorderLayout());
        
        //tworzenie tabeli z aktualnymi zamowieniami
        tabelaZamowien.addColumn("nr_Zamowienia");
        tabelaZamowien.addColumn("Status");
        JTable tabelaPomoznicza = new JTable(tabelaZamowien);
        tabelaPomoznicza.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabelaPomoznicza.setVisible(true);
        tabelaPomoznicza.setPreferredScrollableViewportSize(new Dimension(
        tabelaPomoznicza.getColumnCount() * 150, tabelaPomoznicza.getRowHeight() * 16));
        tabelaPomoznicza.setEnabled(false);
        tabelaPomoznicza.doLayout();
        panelListy.add(new JScrollPane(tabelaPomoznicza));
        tabelaZamowien.setRowCount(0);

        //Ustawianie panelu z przyciskami
        panelZamowienia.setPreferredSize(new Dimension(200, 600));
        panelZamowienia.setLayout(null);
        
        wylogujButton.setText("Wyloguj");
        wylogujButton.setBounds(30, 500, 160, 30);
        
        aktualneZamowieniaButton.setText("Wyswietl zamowienia");
        aktualneZamowieniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //tutaj bedzie pobieranie danych z bazy
                String tab[] = {"1", "Kontener zabiera œmieci na wysypisko"};
                tabelaZamowien.addRow(tab);
            }
        }
        );
        aktualneZamowieniaButton.setBounds(30, 100, 160, 30);
        panelZamowienia.add(wylogujButton);
        panelZamowienia.add(aktualneZamowieniaButton);
        
        //okno z powiadomieniami
        powiadomienie.setText("Aktualizacja dla \ntwojego zamowinia! \nZobacz teraz!");
        powiadomienie.setVisible(true);
        powiadomienie.setPreferredSize(new Dimension(800, 600));
        powiadomienie.setEditable(false);
        powiadomienie.setBounds(30, 300, 160, 50);
        powiadomienie.doLayout();
        panelZamowienia.add(powiadomienie);
        
        //Dodanie paneli do okana
        panelKlientaCaly.add(panelListy, BorderLayout.WEST);
        panelKlientaCaly.add(panelZamowienia, BorderLayout.EAST);
    }
}
