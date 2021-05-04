package iut.lp.projet.localeat;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class ChargementActivity  extends Activity {
    ProgressBar chargementProgression;
    int chargement = 3000; // 3 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargement);

        //Bar de progression
        chargementProgression = findViewById(R.id.splashProgress);
        avancerProgression();

        //Code pour démarrer le minuteur et l'action à effectuer après la fin du minuteur
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Faites n'importe quelle action ici. Nous allons maintenant passer à la page suivante
                Intent mySuperIntent = new Intent(ChargementActivity.this, ChoixConnexionActivity.class);
                startActivity(mySuperIntent);

                //Cette fonction "finish()" permet de quitter l'application lorsque le bouton "retour" est enfoncé à partir de la page d'accueil, qui est ActivityHome.
                finish();

            }
        }, chargement);
    }

    //Méthode pour faire fonctionner la barre de progression pendant 3 secondes
    private void avancerProgression() {
        ObjectAnimator.ofInt(chargementProgression, "progress", 100)
                .setDuration(3000)
                .start();
    }
}
