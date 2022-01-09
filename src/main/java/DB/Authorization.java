package DB;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Authorization {
    private Firestore db;

    public Authorization(Firestore connection){
        this.authStatus = AuthStatus.GUEST;
        this.userName = "";
        this.db = connection;
    }

    public enum AuthStatus {
        GUEST, CLIENT, WASTE_COLLECTOR, OWNER, ADMIN
    }
    private AuthStatus authStatus;
    public String userName;

<<<<<<< Updated upstream:src/main/java/DB/Auth.java
    public DB.Error login(String name, String pass) {
=======
    public AuthStatus authStatusGetter(){
        return this.authStatus;
    }

    public DataBase.Error login(String name, String pass) {
>>>>>>> Stashed changes:src/main/java/DB/Authorization.java
        DocumentReference docRef = db.collection("users").document(name);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (document.exists()) {
            if(pass.equals(document.getString("pass"))){
                authStatus = AuthStatus.CLIENT;
                userName = name;
                return DataBase.Error.GOOD;
            }
            else
                return DataBase.Error.BAD_PASSWORD;
        } else {
            return DataBase.Error.USER_NULL;
        }
    }

    public DataBase.Error register(String name, Map<String, String> data){
        db.collection("users").document(name).set(data);
        return login(name, data.get("pass"));
    }
}