package iut.lp.projet.localeat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import iut.lp.projet.localeat.model.Produit;
import iut.lp.projet.localeat.model.Reservation;

public class ReservationAdapter extends ArrayAdapter<Reservation> {

    private Context context;

    public ReservationAdapter (Context context, ArrayList<Reservation> allReservations) {
        super(context,0,allReservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reservation reservation = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste_reservations_items,parent,false);
        }

        TextView reservationDate = (TextView) convertView.findViewById(R.id.reservationDate);
        TextView reservationProduit = (TextView) convertView.findViewById(R.id.reservationProduit);

        final Date dateReservation = reservation.getDate();

        // Transformation de la date au format dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(dateReservation);

        // Ajout de la date et du nom du producteur de la réservation
        reservationDate.setText(date);
        reservationProduit.setText(reservation.getProduit().getNom());

        return convertView;
    }

}
