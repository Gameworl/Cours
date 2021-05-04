package iut.lp.projet.localeat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;

import iut.lp.projet.localeat.database.Firebase;
import iut.lp.projet.localeat.model.Producteur;

public class FicheProducteur extends AppCompatActivity {

    private Producteur producteur ;
    Firebase fireBase = new Firebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fiche_producteur);

        // Retour à la carte
        Button retour = findViewById(R.id.retour);
        retour.setOnClickListener(view -> finish());

        Button profil = findViewById(R.id.btnProfil);
        profil.setOnClickListener(view ->
                chargerFragment(new ProfilFragment(this.producteur.getImage(),
                        this.producteur.getDescription(),
                        this.producteur.getAdresse(),
                        this.producteur.getHeure(),
                        this.producteur.getNumero()))
        );

        Button produits = findViewById(R.id.btnProduits);
        produits.setOnClickListener(view ->
                chargerFragment(new ProduitsFragment(this.producteur.getProduit(), this))
        );

        //Recuperation du producteur avec son id
        fireBase.getCollectionUseWhere("producteurs", "id", getIntent().getStringExtra("producteur")).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    this.producteur = (document.toObject(Producteur.class));
                }
                remplirInfos();
            } else {
                Log.e("ERROR", "Error getting documents: ", task.getException());
            }
        });

    }

    private void remplirInfos() {
        final String exploitation = this.producteur.getExploitation();

        final TextView champExploitation = findViewById(R.id.exploitation);
        champExploitation.setText(exploitation);

        chargerFragment(new ProfilFragment(this.producteur.getImage(),
                this.producteur.getDescription(),
                this.producteur.getAdresse(),
                this.producteur.getHeure(),
                this.producteur.getNumero()));
    }

    private void chargerFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}

