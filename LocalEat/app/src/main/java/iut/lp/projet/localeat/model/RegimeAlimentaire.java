package iut.lp.projet.localeat.model;

import java.util.List;

public class RegimeAlimentaire {

    String id;
    String nom;
    List<TypeProduit> typeProduit;

    public RegimeAlimentaire(){}

    public RegimeAlimentaire(String id, String nom,  List<TypeProduit>  typeProduit) {
        this.id = id;
        this.nom = nom;
        this.typeProduit = typeProduit;
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

    public  List<TypeProduit>  getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit( List<TypeProduit>  typeProduit) {
        this.typeProduit = typeProduit;
    }
}
