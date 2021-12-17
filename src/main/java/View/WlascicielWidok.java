package View;

import javax.swing.*;
import java.awt.*;

public class WlascicielWidok extends JPanel {
    //konstruktor
    public WlascicielWidok() {
        initComponents();
    }



    //zmienne paneli
    private boolean actualView=true;
    private JPanel panelWlascicielaCaly = new JPanel();
    private JPanel panelTabeliKursow = new JPanel();
    private JPanel panelTabeliKontenerow = new JPanel();
    private JPanel panelZarzadzaniaKursami = new JPanel();
    private JPanel panelZarzadzaniaKontenerami = new JPanel();

    //zmienne komponentow
    private JButton przyciskZmianyWidoku = new JButton("zmien widok");

    //zmienna dla tabeli kontenerow
    private TabelaKontenerow tabelaKontenerow = new TabelaKontenerow();

    //getter
    public JPanel getPanelWlascicielaCaly() {
        return panelWlascicielaCaly;
    }


    public void initComponents(){
        panelWlascicielaCaly.setPreferredSize(new Dimension(800,600));
        panelWlascicielaCaly.setLayout(new BorderLayout());

        panelTabeliKontenerow.setPreferredSize(new Dimension(600,600));
        panelTabeliKontenerow.setLayout(new BorderLayout());
        panelTabeliKontenerow.add(tabelaKontenerow.getTabela(), BorderLayout.CENTER);
        panelTabeliKontenerow.add(tabelaKontenerow.getTabela().getTableHeader(), BorderLayout.NORTH);
        //trzeba zaimplementowac tabele kursow w osobnej klasie
        //nastepnie przypisac tak jak ta tabele, pozniej w klasie okno zmieniac te panele

        panelWlascicielaCaly.add(panelTabeliKontenerow);
        panelWlascicielaCaly.setVisible(true);

    }

    public JButton getPrzyciskZmianyWidoku() {
        return przyciskZmianyWidoku;
    }
}
