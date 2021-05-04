package iut.lp.projet.localeat;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import iut.lp.projet.localeat.database.Firebase;
import iut.lp.projet.localeat.model.Producteur;
import iut.lp.projet.localeat.model.Produit;

public class ProduitsFragment extends Fragment {
    public Context context = null;
    public int[] produits = null;
    public Firebase firebase = new Firebase();
    public ArrayList<Produit> listeProduits = new ArrayList<>();
    public RecyclerView recyclerView = null;

    public ProduitsFragment() {
    }

    public ProduitsFragment(int[] produits, Context context) {
      this.produits = produits;
      this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebase.getCollection("produits").addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    this.listeProduits.add(document.toObject(Produit.class));
                }
                chargerVue();
            } else {
                Log.e("ERROR", "Error getting documents: ", task.getException());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produits, container, false);

        // mise en place de la recycler view
        recyclerView = view.findViewById(R.id.produits_adapter);
        return view;
    }

    public void chargerVue(){
        ProduitsAdapter adapter = new ProduitsAdapter(context, this.listeProduits);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}