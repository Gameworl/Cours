package iut.lp.projet.localeat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import iut.lp.projet.localeat.database.Authentification;
import iut.lp.projet.localeat.database.Fixture;
import iut.lp.projet.localeat.model.Utilisateur;

public class InscriptionActivity  extends Activity {
    private EditText prenom;
    private EditText nom;
    private EditText email;
    private EditText mdp;
    private EditText mdp2;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);
        email = findViewById(R.id.email);
        mdp = findViewById(R.id.motDePasse_inscription);
        Button suivant = findViewById(R.id.suivant);
        mdp2 = findViewById(R.id.confirmation_motDePasse);
        Button retour = findViewById(R.id.buttonRetour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InscriptionActivity.this, ChoixConnexionActivity.class);
                startActivity(intent);
            }
        });


        pd = new ProgressDialog(this);

        suivant.setOnClickListener(v -> {
            String txtprenom = prenom.getText().toString();
            String txtnom = nom.getText().toString();
            String txtEmail = email.getText().toString();
            String txtmdp = mdp.getText().toString();
            String txtmdpConfirmation = mdp2.getText().toString();
            if (TextUtils.isEmpty(txtprenom) || TextUtils.isEmpty(txtnom)
                    || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtmdp)){
                Toast.makeText(InscriptionActivity.this, R.string.champ_invalide, Toast.LENGTH_SHORT).show();
            } else if (txtmdp.length() < 6){
                Toast.makeText(InscriptionActivity.this, R.string.motDePasse_trop_court, Toast.LENGTH_SHORT).show();
            } else if (!txtmdp.equals(txtmdpConfirmation)) {
                Toast.makeText(InscriptionActivity.this, R.string.motDePasse_pas_identiques, Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(InscriptionActivity.this, InscriptionPrefsAlimentairesActivity.class);
                intent.putExtra("nom",txtnom);
                intent.putExtra("prenom",txtprenom);
                intent.putExtra("email",txtEmail);
                intent.putExtra("mdp",txtmdp);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
