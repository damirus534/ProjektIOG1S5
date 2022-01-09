package View;

import Controllers.ListaKontenerow;
import Controllers.ListaZamówień;
import Controllers.StatusZamowienia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Vector;

public class KierowcaWidok extends JPanel {


    //panele
    private JPanel OknoKierowcy=new JPanel();
    private JPanel PanelListyKursow =new JPanel();
    private JPanel PanelButton=new JPanel();
    //tabele
    private JTable TabelaKursow;
    private JTable AktualnyKurs=new JTable();

    //komponety
    private JButton koniecKursuButton =new JButton();
    private JButton wylogujButton =new JButton();
    private JLabel AktualnyKursLabel=new JLabel();
    private JLabel Kursy=new JLabel();
    private JLabel aktualnyUzytkownikLabel = new JLabel();
    //wektory
    private Vector<String> KolumnyWektor= new Vector<String>(3);
    private Vector<Vector<String>> KursyWektor= new Vector<Vector<String>>();
    private Vector<Vector<String>> AktualneWektor=new Vector<>();

    //gettery
    public JPanel getOknoKierowcy() {
        return OknoKierowcy;
    }
    public JButton getWylogujButton() {
        return wylogujButton;
    }
    //tworzenie okna
    public KierowcaWidok(){
        initComponens();
    }
    public KierowcaWidok(ListaZamówień listaZamówień)
    {
        //przy uzyciu listy zamowien dodanie wartosci do wektoru kursow
        for(int i=0;i<listaZamówień.getListaZanowien().size();i++){
            Vector<String> temp=new Vector<>();

            temp.add(listaZamówień.getListaZanowien().get(i).getAdres());
            temp.add(String.valueOf(listaZamówień.getListaZanowien().get(i).getIdKontenera()));
            temp.add(listaZamówień.getListaZanowien().get(i).getData());
            if(listaZamówień.getListaZanowien().get(i).getStatus()!= StatusZamowienia.Zakonczenie){
            if(java.time.LocalDate.now().toString().equals(listaZamówień.getListaZanowien().get(i).getData()))AktualneWektor.add(temp);
            else KursyWektor.add(temp);}


        }
        initComponens();
    }

    //inicjalizacja komponentów
    private void initComponens(){
        //ustawienia okna
        OknoKierowcy.setSize(800,600);
        OknoKierowcy.setLayout(new BorderLayout());
        //ustawiania panela kotenero
        dodanieKolumn();
        TabelaKursow=new JTable(KursyWektor,KolumnyWektor);
        PanelListyKursow.setPreferredSize(new Dimension(600,600));
        TabelaKursow.setFillsViewportHeight(true);
        PanelListyKursow.setLayout(new BoxLayout(PanelListyKursow,BoxLayout.Y_AXIS));
        TabelaKursow.setFillsViewportHeight(true);
        AktualnyKurs=new JTable(AktualneWektor,KolumnyWektor);
        AktualnyKurs.setFillsViewportHeight(true);

        //Dodawanie elementow do panelu z tabelami
        Kursy.setText("Kursy");
        AktualnyKursLabel.setText("Aktualny Kurs");
        Kursy.setFont(new Font("Serif",Font.PLAIN,20));
        AktualnyKursLabel.setFont(new Font("Serif",Font.PLAIN,20));
        PanelListyKursow.setBorder(new EmptyBorder(new Insets(0,0,50,0)));
        PanelListyKursow.add(AktualnyKursLabel,BorderLayout.CENTER);
        PanelListyKursow.add(Box.createRigidArea(new Dimension(0,20)));
        PanelListyKursow.add(AktualnyKurs.getTableHeader());
        PanelListyKursow.add(AktualnyKurs);
        PanelListyKursow.add(Box.createRigidArea(new Dimension(0,50)));
        PanelListyKursow.add(Kursy);
        PanelListyKursow.add(Box.createRigidArea(new Dimension(0,20)));
        PanelListyKursow.add(TabelaKursow.getTableHeader());
        PanelListyKursow.add(TabelaKursow);
        PanelListyKursow.setVisible(true);


        //Ustawianie panelu z buttonami
        PanelButton.setPreferredSize(new Dimension(180,600));
        PanelButton.setLayout(null);

        wylogujButton.setText("Wyloguj");
        wylogujButton.setBounds(10 ,520,160,20);

        koniecKursuButton.setText("Koniec Kursu");
        koniecKursuButton.setBounds(10,70,160,30);
        PanelButton.add(wylogujButton);
        PanelButton.add(koniecKursuButton);
        //Dodanie Labela do panelu z buttonami
        aktualnyUzytkownikLabel.setText("zalogowany jako: Kierowca");
        aktualnyUzytkownikLabel.setBounds(10,0, 160,20);
        PanelButton.add(aktualnyUzytkownikLabel);

        //Dodanie paneli do okana
        OknoKierowcy.add(PanelListyKursow,BorderLayout.WEST);
        OknoKierowcy.add(PanelButton,BorderLayout.EAST);








        OknoKierowcy.setVisible(true);
    }
    //Funckja dodajaca do wektora columny
    private void dodanieKolumn(){
        KolumnyWektor.add("Adres");
        KolumnyWektor.add("Numer Kontenera");
        KolumnyWektor.add("Data rozpoczecia kursu");
    }

}