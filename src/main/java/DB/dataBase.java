package DB;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class dataBase {
    
    public enum Error {
        GOOD, BAD_PASSWORD, USER_NULL
    }

    //db stuff
    private Firestore connectionPlate;

    public Firestore connection(){
        return this.connectionPlate;
    }

    public dataBase(){
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.authPlate = new Authorization(connectionPlate);
        this.tablePlate = new Table(connectionPlate);
    }

    private void connect() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/smietnik-dfef1-firebase-adminsdk-dqvmo-4ab35de67e.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("smietnik-dfef1")
                .build();
        try{
            FirebaseApp.initializeApp(options);
        }catch(Exception e)
        {
            System.out.println("");
        }
        

        this.connectionPlate = FirestoreClient.getFirestore();
    }

    //auth stuff
    private Authorization authPlate;

    public Authorization auth(){
        return this.authPlate;
    }

    //tables stuff
    private Table tablePlate;

    public Table table(String table){
        this.tablePlate.setTable(table);
        return this.tablePlate;
    }
}