package Controllers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DB {
    public enum Error {
        NULL, BAD_PASSWORD, USER_NULL
    }
    private Error error;



    //db stuff
    private Firestore connection;

    public DB(){
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.error = Error.NULL;
        this.authStatus = AuthStatus.GUEST;
        this.userName = "";
    }

    private void connect() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/smietnik-dfef1-firebase-adminsdk-dqvmo-4ab35de67e.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("smietnik-dfef1")
                .build();
        FirebaseApp.initializeApp(options);

        this.connection = FirestoreClient.getFirestore();
    }



    //auth stuff
    public enum AuthStatus {
        GUEST, CLIENT, WASTE_COLLECTOR, OWNER, ADMIN
    }
    private AuthStatus authStatus;
    public String userName;

    public Error login(String name, String pass) {
        error = Error.NULL;
        DocumentReference docRef = this.connection.collection("users").document(name);
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
            }
            else
                error = Error.BAD_PASSWORD;
        } else {
            error = Error.USER_NULL;
        }

        return error;
    }

    public Error register(String name, Map<String, String> data){
        connection.collection("users").document(name).set(data);
        return login(name, data.get("pass"));
    }
}
