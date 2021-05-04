package iut.lp.projet.localeat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;
import java.io.InputStream;

public class PopUpProducteur extends DialogFragment {

    String id;
    String exploitation;
    String numero;
    String adresse;
    String heure;
    String url;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("id", "");
            exploitation = getArguments().getString("exploitation", "");
            numero = getArguments().getString("numero", "");
            adresse = getArguments().getString("adresse", "");
            heure = getArguments().getString("heure", "");
            url = getArguments().getString("url", "");

        }
        View v = inflater.inflate(R.layout.pop_up_producteur, container, false);

        TextView vueExploitation = v.findViewById(R.id.exploitation);
        vueExploitation.setText(exploitation);

        TextView vueNumero = v.findViewById(R.id.numero);
        vueNumero.setText(numero);

        TextView vueAdresse = v.findViewById(R.id.adresse);
        vueAdresse.setText(adresse);

        TextView vueHeure = v.findViewById(R.id.heure);
        String matin = heure.substring(0, heure.indexOf(","));
        String aprem = heure.substring(heure.indexOf(",") + 1);
        String texteHeure = "Ouvert de " + matin.substring(0, matin.indexOf("-")) + " à " + matin.substring(matin.indexOf("-")+1) +
                " et de " + aprem.substring(0, aprem.indexOf("-")) + " à " + aprem.substring(aprem.indexOf("-")+1) ;
        vueHeure.setText(texteHeure);

        ImageView imageProducteur = v.findViewById(R.id.image);
        ChargerImage chargerImage = new ChargerImage(imageProducteur);
        chargerImage.execute(url);

        v.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), FicheProducteur.class);
            intent.putExtra("producteur", id);
            startActivity(intent);
        });

        return v;

    }

    private class ChargerImage extends AsyncTask<String, Void, Bitmap> {

        ImageView imageProducteur;

        public ChargerImage(ImageView imageProducteur) {
            this.imageProducteur = imageProducteur;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageProducteur.setImageBitmap(bitmap);
        }
    }
}


