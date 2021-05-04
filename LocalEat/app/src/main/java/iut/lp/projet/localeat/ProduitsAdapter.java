package iut.lp.projet.localeat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import iut.lp.projet.localeat.model.Produit;


public class ProduitsAdapter extends RecyclerView.Adapter<ProduitsAdapter.MyViewHolder> {

    private ArrayList<Produit> produitsListe;
    private final Context context;

    public ProduitsAdapter(Context context, ArrayList<Produit> produitsList){
        this.context = context;
        this.produitsListe = produitsList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomProduit;
        private final TextView descriptionProduit;
        private ImageButton reservable;

        public MyViewHolder(final View itemView) {
            super(itemView);

            // récupération des éléments de la vue
            nomProduit = itemView.findViewById(R.id.nomProduit);
            descriptionProduit = itemView.findViewById(R.id.descriptionProduit);
            reservable = itemView.findViewById(R.id.reservable);
        }
    }

    @NonNull
    @Override
    public ProduitsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.produit_unique, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Produit produit = produitsListe.get(position);

        // remplissage des éléments
        holder.nomProduit.setText(produit.getNom());
        holder.descriptionProduit.setText(produit.getDescription());

        if (!produit.getReservable()){
            holder.reservable.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return produitsListe.size();
    }

}
