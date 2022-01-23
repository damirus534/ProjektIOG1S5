package View;

import Controllers.Kontener;
import Controllers.ListaKontenerow;
import DB.dataBase;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class TabelaKontenerowTest {

    @Test
    void dodawanieKonteneruTest(){
        Kontener temp = new Kontener(true, "2029-01-01", 1000);

        dataBase db = new dataBase();

        Map<String, Object> data = new HashMap<>();
        data.put("dostepnosc", String.valueOf(temp.najblizszaDostepnosc));
        data.put("status", temp.status);
        data.put("id", temp.idKontenera);

        ApiFuture<WriteResult> future = db.connection().collection("containers").document("1000").set(data);

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertTrue(future.isDone());

        ListaKontenerow kont = new ListaKontenerow(db.table("containers").list());
        assertEquals(kont.konteneryVector.get(kont.getLista().size()-1).getStatus(), temp.getStatus());
        assertEquals(kont.konteneryVector.get(kont.getLista().size()-1).getIdKontenera(), temp.getIdKontenera());
        assertEquals(kont.konteneryVector.get(kont.getLista().size()-1).getNajblizszaDostepnosc(), temp.getNajblizszaDostepnosc());
    }

    @Test
    void usuwanieKonteneru(){
        dataBase db = new dataBase();

        ApiFuture<WriteResult> future1 = db.connection().collection("containers").document("1000").delete();

        try {
            future1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertTrue(future1.isDone());
    }

}
