package iut.lp.projet.localeat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import iut.lp.projet.localeat.database.Firebase;
import iut.lp.projet.localeat.model.Reservation;

public class ReservationDetailsActivity extends Activity {

    private Firebase firebase = new Firebase();
    private Reservation reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);

        // Retour à la carte
        Button retour = findViewById(R.id.boutonRetour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationDetailsActivity.this, ListeReservationsActivity.class);
                startActivity(intent);
            }
        });

        // Récupération du détail de la réservation par son id
        firebase.getCollectionUseWhere("reservations", "id", getIntent().getStringExtra("reservationId")).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    this.reservation = document.toObject(Reservation.class);
                }
                recupererDetails();
            } else {
                Log.e("ERROR", "Error getting documents: ", task.getException());
            }
        });
    }

    // Récupération des éléments de la réservation
    private void recupererDetails() {
        final String nomProduit = this.reservation.getProduit().getNom();

        final TextView textViewNomProduit = findViewById(R.id.detailNomProduit);
        textViewNomProduit.setText(nomProduit);

        // Changement de la couleur du texte en fonction de l'état de la réservation
        final TextView textViewEtat = findViewById(R.id.detailEtat);
        if (reservation.getEtat().toString().equals("ATT")) {
            textViewEtat.setText(R.string.encours_confirmation);
            textViewEtat.setTextColor(Color.parseColor("#F7931C"));
        } else if (reservation.getEtat().toString().equals("CONF")) {
            textViewEtat.setText(R.string.reservation_valide);
            textViewEtat.setTextColor(Color.parseColor("#8CC63F"));
        } else if (reservation.getEtat().toString().equals("REF")) {
            textViewEtat.setText(R.string.reservation_refuse);
            textViewEtat.setTextColor(Color.parseColor("#CF2A2A"));
        }

        final String nomProducteur = this.reservation.getProducteur().getExploitation();

        final TextView textViewNomProducteur = findViewById(R.id.detailNomProducteur);
        textViewNomProducteur.setText(nomProducteur);

        final Date dateReservation = this.reservation.getDate();

        final TextView textViewDateReservation = findViewById(R.id.detailDateReservation);

        // Transformation de la date au format dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String d = dateFormat.format(dateReservation);

        textViewDateReservation.setText(d);

    }

}