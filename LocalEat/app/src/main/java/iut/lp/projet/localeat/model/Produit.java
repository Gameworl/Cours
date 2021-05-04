package iut.lp.projet.localeat.model;

public class Produit {
    String id;
    String description;
    String nom;
    TypeProduit typeProduit;
    Producteur producteur;
    Boolean reservable;

    public Produit() {}

    public Produit(String id, String description, String nom, TypeProduit id_type, Producteur id_producteur, Boolean reservable) {
        this.id = id;
        this.description = description;
        this.nom = nom;
        this.typeProduit = id_type;
        this.producteur = id_producteur;
        this.reservable = reservable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    public Producteur getProducteur() {
        return producteur;
    }

    public void setProducteur(Producteur producteur) {
        this.producteur = producteur;
    }

    public Boolean getReservable() {
        return reservable;
    }

    public void setReservable(Boolean reservable) {
        this.reservable = reservable;
    }
}
