package iut.lp.projet.localeat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;


import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mapbox.geocoder.MapboxGeocoder;
import com.mapbox.geocoder.service.models.GeocoderResponse;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import iut.lp.projet.localeat.database.Firebase;
import iut.lp.projet.localeat.model.Producteur;
import iut.lp.projet.localeat.model.Utilisateur;
import iut.lp.projet.localeat.model.Produit;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CarteActivity extends AppCompatActivity implements menuBasActivity.BottomSheetListener{


    private MapboxMap mapboxMap;
    private MapView mapView;

    private List<Produit> listeProduits = new ArrayList<>();
    private List<Producteur> listeProducteurRecherche = new ArrayList<>();

    private boolean premiereFois = true;

    private EditText produitRechercher;
    private Button buttonRechercher;
    private Button buttonAnnuler;

    Firebase fireBase = new Firebase();
    private final List<Producteur> producteurs  = new ArrayList<>();
    LatLng latLngActuelle = null;
    Utilisateur utilisateur;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String uid = mAuth.getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recuperation de l'utilisateur connecté
        if (uid != null){
            fireBase.getOneDocumentOfCollection("users",uid).addOnSuccessListener(documentSnapshot -> {
                utilisateur = documentSnapshot.toObject(Utilisateur.class);
            });
        }


        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_carte);

        BottomNavigationItemView BoutonSetting = findViewById(R.id.action_settings);
        this.mapView = findViewById(R.id.mapView);
        this.mapView.onCreate(savedInstanceState);

        this.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                    CarteActivity.this.mapboxMap = mapboxMap;
                    }
                });

            }
        });

        // 1ere recuperation des producteurs
        if (this.premiereFois){
            this.premiereFois = false;
            fireBase.getCollection("producteurs").addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        this.producteurs.add(document.toObject(Producteur.class));
                    }
                    if (mapboxMap != null){
                        actualiserMarqueur(this.producteurs);
                    }{
                        this.mapView.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                                    @Override
                                    public void onStyleLoaded(@NonNull Style style) {
                                    }
                                });
                                CarteActivity.this.mapboxMap = mapboxMap;
                                actualiserMarqueur(CarteActivity.this.producteurs);
                            }
                        });
                    }
                } else {
                    Log.e("ERROR", "Error getting documents: ", task.getException());
                }
            });
        }

        // ouverture du menu bas
        BoutonSetting.setOnClickListener(v -> {
            menuBasActivity bottomSheet = new menuBasActivity();
            bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
        });

        this.produitRechercher = findViewById(R.id.edit_search);
        this.buttonRechercher = findViewById(R.id.button_doSearch);
        this.buttonAnnuler = findViewById(R.id.button_annuleSearch);

        this.buttonRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String requete = produitRechercher.getText().toString();
                rechercher(requete);
            }
        });

        this.buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remplirCarte();
            }
        });


        Button menuBouton = findViewById(R.id.menuBouton);
        menuBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarteActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }

    public void actualiserMarqueur(List<Producteur> producteurs){
        this.mapboxMap.clear();
        for (Producteur producteur: producteurs){
            this.mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(producteur.getCoord().getLatitude(), producteur.getCoord().getLongitude()))
                    .title(producteur.getExploitation()));
        }
        this.mapboxMap.setOnMarkerClickListener(marker -> {

            // au clic, on recupere le titre du marker et on cherche dans la liste de producteur a un nom d'exploitation qui correspond
            for(int i = 0; i<producteurs.size(); i++) {
                if(producteurs.get(i).getExploitation().equals(marker.getTitle())) {
                    // on crée le popUp et on lui passe les paramètres dont il a besoin
                    PopUpProducteur popUpProducteur = new PopUpProducteur();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", producteurs.get(i).getId());
                    bundle.putString("exploitation", producteurs.get(i).getExploitation());
                    bundle.putString("numero", producteurs.get(i).getNumero());
                    bundle.putString("heure", producteurs.get(i).getHeure());
                    bundle.putString("adresse", producteurs.get(i).getAdresse());
                    bundle.putString("url", producteurs.get(i).getImage());
                    popUpProducteur.setArguments(bundle);
                    popUpProducteur.show(getSupportFragmentManager(), "DialogFragmentWithSetter");

                }
            }
            return true;
        });
    }

    private void remplirCarte(){
        this.producteurs.clear();
        fireBase.getCollection("producteurs").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    this.producteurs.add(document.toObject(Producteur.class));
                }
                actualiserMarqueur(this.producteurs);
            } else {
                Log.e("ERROR", "Error getting documents: ", task.getException());
            }
        });
    }

    private void rechercher(String query){
        this.listeProduits.clear();
        this.listeProducteurRecherche.clear();
        if (!query.equals("")){
            String lowerCaseQuery = query.toLowerCase();
            fireBase.getCollectionUseWhere("produits","nom",lowerCaseQuery).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        this.listeProduits.add(document.toObject(Produit.class));
                    }
                    for(Produit produit: this.listeProduits){
                        this.listeProducteurRecherche.add(produit.getProducteur());
                    }
                    actualiserMarqueur(this.listeProducteurRecherche);
                    this.produitRechercher.setText("");
                } else {
                    Log.e("ERROR", "Error getting documents: ", task.getException());
                }
            });
        }else {
            remplirCarte();
        }
    }

    /**
     * Utilisation de la methode onAdresse du fragment
     * Geocoder une adresse en coordonnée Gps
     * @param adresse String
     */
    @Override
    public void onAdresse(String adresse)  {
        // Api MapboxGeocoder pour géocoder une adresse
        MapboxGeocoder client = new MapboxGeocoder.Builder()
                .setAccessToken(getString(R.string.mapbox_access_token))
                .setLocation(adresse)
                .build();

        // Callback sur l'adresse que l'on a géocoder
        client.enqueue(new Callback<GeocoderResponse>() {
            // si succes
            @Override
            public void onResponse(Response<GeocoderResponse> response, Retrofit retrofit) {
                mapboxMap.clear();
                actualiserMarqueur(producteurs);
                latLngActuelle =  new LatLng(response.body().getFeatures().get(0).getLatitude(),response.body().getFeatures().get(0).getLongitude());
            }
            // si echec
            @Override
            public void onFailure(Throwable t) {
                Log.e("ERROR", "Error: " + t.getMessage());
            }
        });
    }

    /**
     * Gestion des markers sur la map en fonction de la distance
     * @param distance int
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDistanceSelect(int distance){
        actualiserMarqueur(this.producteurs);
        mapboxMap.getMarkers().forEach(marker -> {
            if (uid != null){
                if (latLngActuelle == null && utilisateur.getCoord() != null) {
                    if ((utilisateur.getCoord().distanceTo(marker.getPosition()) / 1000) > distance) {
                        marker.remove();
                    }
                }
            }
            if (latLngActuelle != null) {
                if ((latLngActuelle.distanceTo(marker.getPosition()) / 1000) > distance) {
                    marker.remove();
                }
            }
        });
        if (uid == null){
            Toast.makeText(this,R.string.creer_compte_ou_adresse,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMsgUser() {
        Toast.makeText(this,R.string.creer_compte,Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}