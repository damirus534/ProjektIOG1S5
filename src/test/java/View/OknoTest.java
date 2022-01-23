package View;

import DB.dataBase;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class OknoTest {
    @Test
    void dodajZamowienieTest()
    {
        dataBase db = new dataBase();

        Map<String, Object> data = new HashMap<>();
        data.put("adres", "testtesttest");
        data.put("containerID", 444);
        data.put("data", "2022-01-30");
        data.put("orderID",1);
        data.put("status","OczekiwaniaNaDostarczenie");
        data.put("username","user2");

        ApiFuture<WriteResult> future = db.connection().collection("orders").document("testtesttest").set(data);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        } catch (ExecutionException e) {
            e.printStackTrace();
            fail();
        }

        db.connection().collection("orders").document("testtesttest").delete();
    }
}