package iut.lp.projet.localeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

public class InscriptionPrefsAlimentairesActivity extends Activity {

    private Spinner spinnerRegimeAlimentaire;
    private String regimeAlimentaire;
    private ArrayList<String> categoriesSelectiones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_prefs_alimentaires);

        //Récupération des données passées dans l'intent
        String nom = getIntent().getStringExtra("nom");
        String prenom = getIntent().getStringExtra("prenom");
        String email = getIntent().getStringExtra("email");
        String mdp = getIntent().getStringExtra("mdp");

        Button suivant = findViewById(R.id.suivantPref);

        //Remplissage du spinner
        this.spinnerRegimeAlimentaire = (Spinner) findViewById(R.id.spinnerRegimeAlimentaire);
        String[] spinRegimeAlimenataire = {"","Omnivore","Végétarien","Pesco-Végétarien","Vegan","Sans lactose"};
        ArrayAdapter<String> regimeAlimentaireAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,spinRegimeAlimenataire);
        regimeAlimentaireAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerRegimeAlimentaire.setAdapter(regimeAlimentaireAdapter);

        this.spinnerRegimeAlimentaire.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                regimeAlimentaire = String.valueOf(spinnerRegimeAlimentaire.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(InscriptionPrefsAlimentairesActivity.this, InscriptionAdresseActivity.class);
                intent.putExtra("nom",nom);
                intent.putExtra("prenom",prenom);
                intent.putExtra("email",email);
                intent.putExtra("mdp",mdp);
                intent.putExtra("regimeAlimentaire",regimeAlimentaire);
                intent.putStringArrayListExtra("categorieListe",categoriesSelectiones);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    public void checkboxSelectionner(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkBox_viande:
                if (checked){
                    this.categoriesSelectiones.add("Viande");
                } else{
                    this.categoriesSelectiones.remove("Viande");
                }
                break;
            case R.id.checkBox_produit_mer:
                if (checked){
                    this.categoriesSelectiones.add("Produits de la mer");
                } else{
                    this.categoriesSelectiones.remove("Produits de la mer");
                }
                break;
            case R.id.checkBox_fruit:
                if (checked){
                    this.categoriesSelectiones.add("Fruits");
                } else{
                    this.categoriesSelectiones.remove("Fruits");
                }
                break;
            case R.id.checkBox_legume:
                if (checked){
                    this.categoriesSelectiones.add("Legumes");
                } else{
                    this.categoriesSelectiones.remove("Legumes");
                }
                break;
            case R.id.checkBox_laitage:
                if (checked){
                    this.categoriesSelectiones.add("Laitages");
                } else{
                    this.categoriesSelectiones.remove("Laitages");
                }
                break;
            case R.id.checkBox_cereale:
                if (checked){
                    this.categoriesSelectiones.add("Céréales");
                } else{
                    this.categoriesSelectiones.remove("Céréales");
                }
                break;
        }
    }
}