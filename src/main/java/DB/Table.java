package DB;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//todo
public class Table {
    private Firestore db;
    private String currentTable;

    public Table(Firestore connection){
        this.db = connection;
        this.currentTable = "users";
    }

    protected void setTable(String table){
        this.currentTable = table;
    }

    //lists data in table
    public Map<String, Object> list(){
        Map<String, Object> to_return = new HashMap<>();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection(currentTable).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (QueryDocumentSnapshot document : documents) {
            to_return.put(document.getId(), document.getData());
        }

        return to_return;
    }

    //adds document with specified id
    public dataBase.Error add(String id, Map<String, Object> data){
        db.collection(currentTable).document(id).set(data);
        return dataBase.Error.GOOD;
    }

    //adds document with auto id
    public dataBase.Error add(Map<String, Object> data){
        db.collection(currentTable).document().set(data);
        return dataBase.Error.GOOD;
    }



    //edits document with given id
    public dataBase.Error edit(String id, String field, Object value){
        db.collection(currentTable).document(id).update(field, value);
        return dataBase.Error.GOOD;
    }


    //deletes doc
    public dataBase.Error delete(String id){
        db.collection(currentTable).document(id).delete();
        return dataBase.Error.GOOD;
    }
}
