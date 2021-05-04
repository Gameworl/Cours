package iut.lp.projet.localeat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;

import iut.lp.projet.localeat.database.Authentification;
import iut.lp.projet.localeat.database.Firebase;
import iut.lp.projet.localeat.model.RegimeAlimentaire;
import iut.lp.projet.localeat.model.TypeProduit;
import iut.lp.projet.localeat.model.Utilisateur;

public class InscriptionAdresseActivity extends Activity {

    private ArrayList<String> categoriesSelectiones = new ArrayList<>();
    ProgressDialog pd;
    private Authentification mAuthentification;
    private FirebaseAuth mAuth;

    private EditText adresse;
    private EditText adresseComplementaire;
    private EditText ville;
    private EditText codePostal;
    private Firebase firebase = new Firebase();
    private RegimeAlimentaire regimeAlimentaire;
    private List<TypeProduit> typeProduits = new ArrayList<>();
    private List<TypeProduit> listeCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_adresse);

        mAuthentification = new Authentification();
        mAuth = FirebaseAuth.getInstance();

        //Récupération des données passées dans l'intent
        String nom = getIntent().getStringExtra("nom");
        String prenom = getIntent().getStringExtra("prenom");
        String email = getIntent().getStringExtra("email");
        String mdp = getIntent().getStringExtra("mdp");
        String stringRegimeAlimentaire = getIntent().getStringExtra("regimeAlimentaire");
        categoriesSelectiones = getIntent().getStringArrayListExtra("categorieListe");

        Button valider = findViewById(R.id.valider);

        this.adresse = findViewById(R.id.edit_adresse);
        this.adresseComplementaire = findViewById(R.id.edit_adresse_complementaire);
        this.ville = findViewById(R.id.edit_ville);
        this.codePostal = findViewById(R.id.edit_codePostal);

        firebase.getCollection("typesProduits").addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()){
                    this.typeProduits.add(document.toObject(TypeProduit.class));
                }
                //remplissage du tableau du choix des categories avec des objects de type TypeProduit
                for (String string: categoriesSelectiones){
                    switch (string) {
                        case "Viande":
                            this.listeCategories.add(this.typeProduits.get(0));
                            break;
                        case "Legume":
                            this.listeCategories.add(this.typeProduits.get(3));
                            break;
                        case "Fruit":
                            this.listeCategories.add(this.typeProduits.get(1));
                            break;
                        case "Produit de la mer":
                            this.listeCategories.add(this.typeProduits.get(5));
                            break;
                        case "Laitage":
                            this.listeCategories.add(this.typeProduits.get(2));
                            break;
                        case "Céréale":
                            this.listeCategories.add(this.typeProduits.get(4));
                            break;
                    }
                }
            }else {
                Log.e("ERROR","Error getting document : ", task.getException());
            }
        });

        //Affectation du régime alimentaire
        firebase.getCollectionUseWhere("regimeAlimentaires","nom",stringRegimeAlimentaire).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                for (QueryDocumentSnapshot document : task.getResult()){
                    this.regimeAlimentaire = document.toObject(RegimeAlimentaire.class);
                }
            }else {
                Log.e("ERROR","Error getting document : ", task.getException());
            }
        });

        pd = new ProgressDialog(this);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adr = adresse.getText().toString();
                String adrCompl = adresseComplementaire.getText().toString();
                String v = ville.getText().toString();
                String cp = codePostal.getText().toString();

                String adresseComplete = adr +","+ adrCompl+"," + v+"," + cp;

                inscriptionUtilisateur(prenom,nom,email,mdp,adresseComplete,regimeAlimentaire,listeCategories);
            }
        });

    }

    private void inscriptionUtilisateur(String prenom, String nom, final String email, String mdp, String adresse, RegimeAlimentaire regimeAlimentaire, List<TypeProduit> offresTypeProduit) {

        pd.setMessage(getResources().getString(R.string.creation_du_compte));
        pd.show();

        mAuth.createUserWithEmailAndPassword(email , mdp).addOnSuccessListener(authResult -> {
             LatLng p1 = new LatLng(46.160329,-1.151139);
            Utilisateur utilisateur = new Utilisateur(mAuth.getCurrentUser().getUid(),prenom,nom,email,adresse,regimeAlimentaire,offresTypeProduit,null,null,p1);
            mAuthentification.ajoutUtilisateur(utilisateur,pd);

            Intent intent = new Intent(InscriptionAdresseActivity.this , CarteActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();


        }).addOnFailureListener(e -> {
            pd.dismiss();
            Toast.makeText(InscriptionAdresseActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}