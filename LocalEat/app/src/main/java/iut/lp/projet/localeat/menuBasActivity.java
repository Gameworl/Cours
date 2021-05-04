package iut.lp.projet.localeat;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import iut.lp.projet.localeat.database.Firebase;
import iut.lp.projet.localeat.model.Utilisateur;

/**
 * Creation d'un fragment qui contiendra notre menu Base Ouvert
 */
public class menuBasActivity extends BottomSheetDialogFragment {
    private BottomSheetListener menuBas;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String uid = mAuth.getUid();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navigation_bas, container, false);

        // liens entre les variables du code et du xml
        ImageView validerAdresse = v.findViewById(R.id.validerAdresse);
        SeekBar distance = (SeekBar) v.findViewById(R.id.seekBar);
        TextView distanceAfficher = v.findViewById(R.id.distance);
        distanceAfficher.setText(R.string.seekBar_base_texte);
        EditText monAdresse = v.findViewById(R.id.monAdresse);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch adresseUtilise = v.findViewById(R.id.monAdresseProfil);
        adresseUtilise.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                if (uid == null){
                    adresseUtilise.setChecked(false);
                    menuBas.onMsgUser();
                }
            }
        });

        //Gestion des evenements quand on bouge la seekBar
        distance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                distanceAfficher.setText(String.format("Distance de recherche (%d km)", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                menuBas.onDistanceSelect(seekBar.getProgress());
            }
        });

        // Lors du click sur le bouton pour valider l'adresse
        validerAdresse.setOnClickListener(v1 -> {
            try {
                if (!adresseUtilise.isChecked() && monAdresse.getText().length() != 0)
                menuBas.onAdresse(String.valueOf(monAdresse.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // retourn la View
        return v;
    }

    /**
     * interface des fonctions à exporter
     */
    public interface BottomSheetListener {
        void onAdresse(String text) throws IOException;
        void onDistanceSelect(int distance);
        void onMsgUser();
    }

    /**
     * Associer le fragment a une activité
     * @param context Context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            menuBas = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

}
