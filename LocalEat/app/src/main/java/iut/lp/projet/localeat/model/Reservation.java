package iut.lp.projet.localeat.model;

import java.util.Date;

public class Reservation {

    String id;
    Produit produit;
    Date date;
    Producteur producteur;
    Utilisateur utilisateur;
    ReservationEtat etat;

    public Reservation() { }

    public Reservation(String id, Produit produit, Date date, Producteur producteur, Utilisateur utilisateur, ReservationEtat etat) {
        this.id = id;
        this.produit = produit;
        this.date = date;
        this.producteur = producteur;
        this.utilisateur = utilisateur;
        this.etat = etat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Producteur getProducteur() {
        return producteur;
    }

    public void setProducteur(Producteur producteur) {
        this.producteur = producteur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ReservationEtat getEtat() {
        return etat;
    }

    public void setEtat(ReservationEtat etat) {
        this.etat = etat;
    }
}
