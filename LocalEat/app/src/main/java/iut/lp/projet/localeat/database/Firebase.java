package iut.lp.projet.localeat.database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Firebase {
    private final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();

    public Task<QuerySnapshot> getCollection(String collectionName){
        return mFirestore.collection(collectionName).get();
    }

    public Task<DocumentSnapshot> getOneDocumentOfCollection(String collectionName, String documentName){
        return mFirestore.collection(collectionName).document(documentName).get();
    }

    public Task<QuerySnapshot> getCollectionUseWhere(String collectionName, String concernedField ,String cond){
        return  mFirestore.collection(collectionName).whereEqualTo(concernedField, cond).get();

    }
}
