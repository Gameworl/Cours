package iut.lp.projet.localeat.model;

public class TypeProduit {
    String id;
    String nom;

    public TypeProduit() {}

    public TypeProduit(String id, String nom) {
        this.id = id;
        this.nom = nom;
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
}
