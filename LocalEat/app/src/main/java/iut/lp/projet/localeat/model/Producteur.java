package iut.lp.projet.localeat.model;

import androidx.annotation.Nullable;

import com.mapbox.mapboxsdk.geometry.LatLng;
import java.util.List;

public class Producteur {
    public String id;
    public String exploitation;
    public String adresse ;
    public String heure ;
    public LatLng coord;
    public String numero ;
    public String description;
    public List<TypeProduit> typeProduit;
    public String image;
    public int[] produit;
    public boolean favori;

    public Producteur() {
    }

    public int[] getProduit() {
        return produit;
    }

    public void setProduit(int[] produit) {
        this.produit = produit;
    }

    public boolean getFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public Producteur(String id, String exploitation, String adresse, String heure, LatLng coord, String numero, String description, List<TypeProduit> typeProduit, @Nullable String image, boolean favori, int[] produit) {
        this.id = id;
        this.exploitation = exploitation;
        this.adresse = adresse;
        this.heure = heure;
        this.coord = coord;
        this.numero = numero;
        this.description = description;
        this.typeProduit = typeProduit;
        this.image = image;
        this.produit = produit;
        this.favori = favori;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExploitation() {
        return exploitation;
    }

    public void setExploitation(String exploitation) {
        this.exploitation = exploitation;
    }

    public LatLng getCoord() {
        return coord;
    }

    public void setCoord(LatLng coord) {
        this.coord = coord;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  List<TypeProduit>  getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit( List<TypeProduit>  typeProduit) {
        this.typeProduit = typeProduit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
