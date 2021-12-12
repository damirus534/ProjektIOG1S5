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
import java.util.concurrent.ExecutionException;

public class DB {
    private Firestore connection;

    public DB(){
        try {
            this.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String login(String name, String pass) {
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
            if(pass.equals(document.getString("pass")))
                return "0";
            else
                return "bad password";
        } else {
            return "user doesn't exist";
        }

//        return "something went wrong";
    }
}
