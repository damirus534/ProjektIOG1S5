package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
        
        //dodanie tabeli kontenerow do layoutu
        panelListy.add(tabelaKontenerow.getTabela(), BorderLayout.CENTER);
        panelListy.add(tabelaKontenerow.getTabela().getTableHeader(), BorderLayout.NORTH);

        //Ustawianie panelu z przyciskami
        panelZamowienia.setPreferredSize(new Dimension(200, 600));
        panelZamowienia.setLayout(null);
        
        //utworzenie przycisku wyloguj
        wylogujButton.setText("Wyloguj");
        wylogujButton.setBounds(30, 500, 160, 30);
        
        //utworzenie przycisku wyswietl zamowienia
        aktualneZamowieniaButton.setText("Wyswietl zamowienia");
        aktualneZamowieniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
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
