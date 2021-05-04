package iut.lp.projet.localeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AuthentificationActivity extends Activity {
    private FirebaseAuth mAuth;

    private EditText email;
    private EditText mdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        email = findViewById(R.id.email);
        mdp = findViewById(R.id.motDePasse);
        Button connexion = findViewById(R.id.connexion);
        Button retour = findViewById(R.id.buttonRetour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthentificationActivity.this, ChoixConnexionActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        connexion.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_mdp = mdp.getText().toString();

            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_mdp)){
                Toast.makeText(AuthentificationActivity.this, R.string.champ_vide, Toast.LENGTH_SHORT).show();
            } else {
                loginUser(txt_email , txt_mdp);
            }
        });
    }

    private void loginUser(String email, String mdp) {

        mAuth.signInWithEmailAndPassword(email , mdp).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Intent intent = new Intent(AuthentificationActivity.this , CarteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(e -> Toast.makeText(AuthentificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
