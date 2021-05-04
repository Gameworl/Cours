package iut.lp.projet.localeat;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

import iut.lp.projet.localeat.database.Firebase;
import iut.lp.projet.localeat.model.Reservation;

public class ListeReservationsActivity extends Activity {

    private Firebase firebase = new Firebase();
    private ArrayList<Reservation> listeReservations = new ArrayList<>();
    private ListView listeViewReservation;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_reservations);

        // Retour à la carte
        Button retour = findViewById(R.id.boutonRetour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListeReservationsActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        adapter = new ReservationAdapter(this, listeReservations);
        this.listeViewReservation = (ListView) findViewById(R.id.listeReservations);
        this.listeViewReservation.setAdapter(adapter);

        firebase.getCollection("reservations").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    this.listeReservations.add(document.toObject(Reservation.class));
                }
                adapter.notifyDataSetChanged();
            } else {
                Log.e("ERROR", "Error getting documents: ", task.getException());
            }
        });

        // Envoi de la réservation par son id
        this.listeViewReservation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListeReservationsActivity.this, ReservationDetailsActivity.class);
                String Idreservation = listeReservations.get(position).getId();
                intent.putExtra("reservationId", Idreservation);
                startActivity(intent);
                finish();
            }
        });

    }

}