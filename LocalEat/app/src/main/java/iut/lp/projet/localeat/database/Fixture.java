package iut.lp.projet.localeat.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import iut.lp.projet.localeat.model.Offre;
import iut.lp.projet.localeat.model.Producteur;
import iut.lp.projet.localeat.model.Produit;
import iut.lp.projet.localeat.model.RegimeAlimentaire;
import iut.lp.projet.localeat.model.Reservation;
import iut.lp.projet.localeat.model.ReservationEtat;
import iut.lp.projet.localeat.model.TypeProduit;
import iut.lp.projet.localeat.model.Utilisateur;

public class Fixture {
    private final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Firebase fireBase = new Firebase();
    String uid = mAuth.getUid();
    private Utilisateur utilisateur;

    private final TypeProduit typeProduit1 = new TypeProduit("1","Viande");
    private final TypeProduit typeProduit2 = new TypeProduit("2","Legume");
    private final TypeProduit typeProduit3 = new TypeProduit("3","Fruit");
    private final TypeProduit typeProduit4 = new TypeProduit("4","Produit de la mer");
    private final TypeProduit typeProduit5 = new TypeProduit("5","Laitage");
    private final TypeProduit typeProduit6 = new TypeProduit("6","Céréale");

    //private final List<TypeProduit> listOmnivore = Collections.singletonList(typeProduit1,typeProduit2,typeProduit3,typeProduit4,typeProduit5,typeProduit6);
    private final List<TypeProduit> listOmnivore = Arrays.asList(typeProduit1,typeProduit2,typeProduit3,typeProduit4,typeProduit5,typeProduit6);
    private final List<TypeProduit>  listVegetarien = Arrays.asList(typeProduit2, typeProduit3,typeProduit5,typeProduit6);
    private final List<TypeProduit>  listPesco = Arrays.asList(typeProduit2, typeProduit3, typeProduit4,typeProduit5,typeProduit6);
    private final List<TypeProduit>  listVegan = Arrays.asList(typeProduit2, typeProduit3, typeProduit6);
    private final List<TypeProduit> listLactose = Arrays.asList(typeProduit1,typeProduit2,typeProduit3,typeProduit4,typeProduit6);



    private final LatLng p1 = new LatLng(48.235,88.21);
    private final LatLng p2 = new LatLng(45.3,89.302);
    private final LatLng p3 = new LatLng(9.02,78.32);

    private final Producteur producteur1 = new Producteur("1","A la bonne franquette","4 rue de la marne","16h20",p1,"0690121310","Description",listOmnivore,null,false,null);
    private final Producteur producteur2 = new Producteur("2","Au Bonne Poire","10 rue de la Marne","10h00",p2,"0656123212","Des bon légumes",listOmnivore,null,false,null);
    private final Producteur producteur3 = new Producteur("3","Chez Mimi","20 rue de la marne","10h30",p3,"0698675423","",listOmnivore,null,true,null);

    //private final RegimeAlimentaire regimeAlimentaire1 = new RegimeAlimentaire("1","Carnivore",listTP);
    private final RegimeAlimentaire regimeAlimentaireOmnivore = new RegimeAlimentaire("1","Omnivore",listOmnivore);
    private final RegimeAlimentaire regimeAlimentaireVegetarien = new RegimeAlimentaire("2","Vegetarien",listVegetarien);
    private final RegimeAlimentaire regimeAlimentairePesco = new RegimeAlimentaire("3","Pesco-Végétarien",listPesco);
    private final RegimeAlimentaire regimeAlimentaireVegan = new RegimeAlimentaire("4","Vegan",listVegan);
    private final RegimeAlimentaire regimeAlimentaireLactose = new RegimeAlimentaire("4","Sans lactose",listLactose);


    private final Date d1 = new Date();
    private final Offre offre1 = new Offre("1","Promo",d1,null,"promo",producteur1);

    private final Produit produit1 = new Produit("1","Patate douce tres douce","patate douce",typeProduit2,producteur1,true);
    private final Produit produit2 = new Produit("2","Viande de boeuf bien tendre","viande de boeuf",typeProduit1,producteur1,false);
    private final Produit produit3 = new Produit("3","Foie gras de tradition","Foie gras",typeProduit1,producteur1,true);
    private final Produit produit4 = new Produit("4","Pomme bien fraiche","Pomme",typeProduit3,producteur3,false);

    public void loadFixture(){

        fireBase.getCollection("reservations").addOnSuccessListener(queryDocumentSnapshots -> {
            if(queryDocumentSnapshots.isEmpty()){
                fireBase.getOneDocumentOfCollection("users",uid).addOnSuccessListener(documentSnapshot -> {
                    utilisateur = documentSnapshot.toObject(Utilisateur.class);
                    Reservation reservation = new Reservation("1", produit3, d1, producteur1, utilisateur, ReservationEtat.ATT);
                    mFirestore.collection("reservations").document().set(reservation);
                });
            }
        });


        fireBase.getCollection("typesProduits").addOnSuccessListener(queryDocumentSnapshots -> {
            if(queryDocumentSnapshots.isEmpty()){
                mFirestore.collection("typesProduits").document().set(typeProduit1);
                mFirestore.collection("typesProduits").document().set(typeProduit2);
                mFirestore.collection("typesProduits").document().set(typeProduit3);
                mFirestore.collection("typesProduits").document().set(typeProduit4);
                mFirestore.collection("typesProduits").document().set(typeProduit5);
                mFirestore.collection("typesProduits").document().set(typeProduit6);
            }
        });

        fireBase.getCollection("producteurs").addOnSuccessListener(queryDocumentSnapshots -> {
            if(queryDocumentSnapshots.isEmpty()){
                mFirestore.collection("producteurs").document().set(producteur1);
                mFirestore.collection("producteurs").document().set(producteur2);
                mFirestore.collection("producteurs").document().set(producteur3);
            }
        });

        fireBase.getCollection("regimeAlimentaires").addOnSuccessListener(queryDocumentSnapshots -> {
            if(queryDocumentSnapshots.isEmpty()){
                mFirestore.collection("regimeAlimentaires").document().set(regimeAlimentaireOmnivore);
                mFirestore.collection("regimeAlimentaires").document().set(regimeAlimentaireVegetarien);
                mFirestore.collection("regimeAlimentaires").document().set(regimeAlimentairePesco);
                mFirestore.collection("regimeAlimentaires").document().set(regimeAlimentaireVegan);
                mFirestore.collection("regimeAlimentaires").document().set(regimeAlimentaireLactose);
            }
        });

        fireBase.getCollection("offres").addOnSuccessListener(queryDocumentSnapshots -> {
            if(queryDocumentSnapshots.isEmpty()){
                mFirestore.collection("offres").document().set(offre1);
            }
        });

        fireBase.getCollection("produits").addOnSuccessListener(queryDocumentSnapshots -> {
            if(queryDocumentSnapshots.isEmpty()){
                mFirestore.collection("produits").document().set(produit1);
                mFirestore.collection("produits").document().set(produit2);
                mFirestore.collection("produits").document().set(produit3);
                mFirestore.collection("produits").document().set(produit4);
            }
        });

    }
}
