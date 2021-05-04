package iut.lp.projet.localeat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class ProfilFragment extends Fragment {

    public String image = null;
    public String description = null;
    public String adresse = null;
    public String heure = null;
    public String numero = null;

    public ProfilFragment() {
        // Required empty public constructor
    }

    public ProfilFragment(String image, String description, String adresse, String heure, String numero) {
        this.image = image;
        this.description = description;
        this.adresse = adresse;
        this.heure = heure;
        this.numero = numero;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        TextView texteAdresse = view.findViewById(R.id.ficheAdresse);
        texteAdresse.setText(adresse);

        TextView horaires = view.findViewById(R.id.ficheHoraires);

        String matin = heure.substring(0, heure.indexOf(","));
        String aprem = heure.substring(heure.indexOf(",") + 1);
        String texteHeure = "Ouvert de " + matin.substring(0, matin.indexOf("-")) + " à " + matin.substring(matin.indexOf("-")+1) +
                " et de " + aprem.substring(0, aprem.indexOf("-")) + " à " + aprem.substring(aprem.indexOf("-")+1) ;
        horaires.setText(texteHeure);

        TextView desc = view.findViewById(R.id.ficheDescription);
        desc.setText(description);

        TextView num = view.findViewById(R.id.ficheNumero);
        num.setText(numero);

        ImageView imageProducteur = view.findViewById(R.id.ficheImage);
        ChargerImage chargerImage = new ChargerImage(imageProducteur);
        chargerImage.execute(image);

        TextView retourCarte = view.findViewById(R.id.retourCarte);
        retourCarte.setOnClickListener(v -> {
            getActivity().finish();
        });

        return view;
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