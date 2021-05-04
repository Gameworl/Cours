package iut.lp.projet.localeat.model;

import androidx.annotation.Nullable;

import java.util.Date;

public class Offre {
    String id;
    String titre;
    Date dateDebut;
    Date dateFin;
    String description;
    Producteur producteur;

    public Offre(String id, String titre, Date dateDebut, @Nullable Date dateFin, String description, Producteur idProducteur) {
        this.id = id;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.producteur = idProducteur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Producteur getIdProducteur() {
        return producteur;
    }

    public void setIdProducteur(Producteur idProducteur) {
        this.producteur = idProducteur;
    }
}
