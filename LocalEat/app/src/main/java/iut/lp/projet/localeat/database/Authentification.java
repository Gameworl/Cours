package iut.lp.projet.localeat.database;

import android.app.ProgressDialog;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import iut.lp.projet.localeat.model.Reservation;
import iut.lp.projet.localeat.model.ReservationEtat;
import iut.lp.projet.localeat.model.Utilisateur;

public class Authentification {

    private final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Firebase fireBase = new Firebase();
    String uid = mAuth.getUid();
    private Utilisateur utilisateur;

    public void ajoutUtilisateur(Utilisateur utilisateur, @Nullable ProgressDialog pd){
        mFirestore.collection("users").document(utilisateur.getId()).set(utilisateur).addOnSuccessListener(aVoid -> pd.dismiss());
    }

}
