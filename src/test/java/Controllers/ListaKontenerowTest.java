package Controllers;

import DB.dataBase;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ListaKontenerowTest {

    @Test
    void zmienStatusKontenera() {
        dataBase db = new dataBase();

        Map<String, Object> data = new HashMap<>();
        data.put("status", false);

        ApiFuture<WriteResult> dodawanieRekordu = db.connection().collection("containers").document("testtesttest").set(data);
        try {
            dodawanieRekordu.get();
            data.replace("status", true);
            ApiFuture<WriteResult> zmianaRekordu = db.connection().collection("containers").document("testtesttest").update(data);
            zmianaRekordu.get();
            if((boolean) db.connection().collection("containers").document("testtesttest").get().get().getData().get("status") == false)
                fail();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        } catch (ExecutionException e) {
            e.printStackTrace();
            fail();
        }

        db.connection().collection("containers").document("testtesttest").delete();
    }
}