package iut.lp.projet.localeat.model;

import androidx.annotation.Nullable;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.sql.Blob;
import java.util.List;

public class Utilisateur {
    String id;
    String nom;
    String prenom;
    String adresse;
    String mail;
    RegimeAlimentaire regimeAlimentaire;
    List<TypeProduit> offreTypeProduit;
    Producteur[] ProducteursFavoris;
    LatLng coord;
    Blob image;

    public Utilisateur(String id, String nom, String prenom, String mail, @Nullable String adresse, @Nullable RegimeAlimentaire regimeAlimentaire , @Nullable List<TypeProduit> offreTypeProduit, @Nullable Producteur[] producteursFavoris, @Nullable Blob image, @Nullable  LatLng coord) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.regimeAlimentaire = regimeAlimentaire;
        this.offreTypeProduit = offreTypeProduit;
        ProducteursFavoris = producteursFavoris;
        this.image = image;
        this.coord = coord;
    }

    public Utilisateur() {
    }

    public LatLng getCoord() {
        return coord;
    }

    public void setCoord(LatLng coord) {
        this.coord = coord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public RegimeAlimentaire getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public void setRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) {
        this.regimeAlimentaire = regimeAlimentaire;
    }

    public List<TypeProduit> getOffreTypeProduit() {
        return offreTypeProduit;
    }

    public void setOffreTypeProduit(List<TypeProduit> offreTypeProduit) {
        this.offreTypeProduit = offreTypeProduit;
    }

    public Producteur[] getProducteursFavoris() {
        return ProducteursFavoris;
    }

    public void setProducteursFavoris(Producteur[] producteursFavoris) {
        ProducteursFavoris = producteursFavoris;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
