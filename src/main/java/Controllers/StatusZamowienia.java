package Controllers;

public enum StatusZamowienia {
    //typ wyliczeniowy używany przy zamówieniu

    OczekiwaniaNaDostarczenie,DostarczenieDoKlienta,DostarcznieDoWysypiska,Zakonczenie;

    public static StatusZamowienia[] wartosci = values();

    public StatusZamowienia kolejnyStatusZamowienia(){
        return wartosci[(this.ordinal()+1) % wartosci.length];
    }


}
