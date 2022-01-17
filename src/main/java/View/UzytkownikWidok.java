package View;


import Controllers.ListaKontenerow;

import javax.swing.*;
import java.awt.*;

public class UzytkownikWidok extends JPanel{

    //metoda ktora inicjuje panel uzytkownika
    public UzytkownikWidok(){
        initComponents();
    }

    public UzytkownikWidok(ListaKontenerow listaKontenerow){
        //uzycie listy kontenrow z bazy danych
        tabelaKontenerow=new TabelaKontenerow(listaKontenerow);
        initComponents();
    }


    //zmienne dla paneli znajdujacych sie w oknie
    private JPanel panelUzytkownikaCaly = new JPanel();
    private JPanel panelLogowania = new JPanel();
    private JPanel panelListy = new JPanel();

    //zmienne dla paneluLogowania
    private JLabel labelLogin= new JLabel();
    private JLabel labelHaslo= new JLabel();

    private JTextField textFieldLogin = new JTextField("admin"); //szybsze logowanie mozna potem wywalic
    private JTextField textFieldHaslo = new JTextField("admin");
    private JButton buttonZaloguj = new JButton();
    private TabelaKontenerow tabelaKontenerow = new TabelaKontenerow();




    //gettery
    public JPanel getPanelUzytkownikaCaly() {
        return panelUzytkownikaCaly;
    }
    public JButton getButtonZaloguj() {
        return buttonZaloguj;
    }
    public JTextField getTextFieldHaslo() {
        return textFieldHaslo;
    }
    public JTextField getTextFieldLogin() {
        return textFieldLogin;
    }

    private void initComponents() {//metoda do edycji

        //tworzenie panelu w ktorym wyswietla sie dwa panele
        panelUzytkownikaCaly.setPreferredSize(new Dimension(800,600));
        panelUzytkownikaCaly.setLayout(new BorderLayout());

        //tworzenie panelu na ktorym bedzie wyswietlac sie lista
        panelListy.setPreferredSize(new Dimension(600,600));
        panelListy.setLayout(new BorderLayout());
        panelListy.add(tabelaKontenerow.getTabela(), BorderLayout.CENTER);
        panelListy.add(tabelaKontenerow.getTabela().getTableHeader(), BorderLayout.NORTH);

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
        buttonZaloguj.setText("zaloguj sie");
        panelLogowania.add(labelLogin);
        panelLogowania.add(labelHaslo);
        panelLogowania.add(textFieldLogin);
        panelLogowania.add(textFieldHaslo);
        panelLogowania.add(buttonZaloguj);
        panelLogowania.setVisible(true);


        panelUzytkownikaCaly.add(panelListy, BorderLayout.WEST);
        panelUzytkownikaCaly.add(panelLogowania, BorderLayout.EAST);
        panelUzytkownikaCaly.setVisible(true);
    }

}