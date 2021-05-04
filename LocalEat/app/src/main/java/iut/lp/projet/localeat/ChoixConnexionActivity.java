package iut.lp.projet.localeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoixConnexionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_connexion);

        Button connexion = findViewById(R.id.buttonConnexion);
        Button creerCompte = findViewById(R.id.buttonCreerCompte);
        Button poursuivreSansConnexion = findViewById(R.id.buttonPoursuivreSansConnexion);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixConnexionActivity.this, AuthentificationActivity.class);
                startActivity(intent);
            }
        });

        creerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixConnexionActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

        poursuivreSansConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixConnexionActivity.this, CarteActivity.class);
                startActivity(intent);
            }
        });
    }
}