package Controllers;

import java.util.Vector;

public class ListaKontenerow extends Kontener{

    public Vector<Kontener> konteneryVector = new Vector<Kontener>();

    public ListaKontenerow(){

    }


    public void ListaKontenerowDodajKontener(Kontener KONTENER) {
        konteneryVector.add(KONTENER);
    }
}
