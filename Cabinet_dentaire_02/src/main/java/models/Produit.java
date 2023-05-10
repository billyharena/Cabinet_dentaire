package models;

import database.Utility;

import java.sql.Connection;
import java.util.List;

public class Produit extends  BaseModel {
    String nom;
    Double prixUnitaire;
    Double prixvente;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getPrixvente() {
        Double prix = 0.00;
        if (getPrixUnitaire() >= 1 && getPrixUnitaire() <= 10000) prix = ((prixUnitaire * 50)/100)+prixUnitaire;
        if (getPrixUnitaire() > 10000 && getPrixUnitaire() <= 50000) prix = ((prixUnitaire * 25)/100)+prixUnitaire;
        if (getPrixUnitaire() > 50000 && getPrixUnitaire() <= 100000) prix = ((prixUnitaire * 15)/100)+prixUnitaire;
        if (getPrixUnitaire() > 100000 ) prix = ((prixUnitaire * 10)/100)+prixUnitaire;
        return prix;
    }

    public Double benefice(){
        Double prix = 0.00;
        prix = getPrixvente() - getPrixUnitaire();
        return prix;
    }
    public void setPrixvente(Double prixvente) {
        this.prixvente = prixvente;
    }

    public Produit(){}

    public Produit(String nom, Double prixUnitaire, Double prixvente) {
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.prixvente = prixvente;
    }

    public Produit(Integer id, String nom, Double prixUnitaire, Double prixvente) {
        super(id);
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
        this.prixvente = prixvente;
    }

    public void insererProduit(Connection conn) throws Exception{
        inserer(this, conn);
        System.out.println("Insertion employé réussi !");
    }

    public Produit[] selection(Produit vue) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(vue);
        Produit[] valeur =(Produit[]) ob.toArray(new Produit[1]);
        return valeur;
    }
    // INSERTION SANS CONNEXION ET FERMER
    public void insererProduit() throws Exception{
        inserer(this);
    }

    public static void main(String[] args) throws Exception {
        Produit p = new Produit();
        p.setId(1);
        Produit[] list = new Produit().selection(p);
        for (int i =0; i < list.length; i++){
            System.out.println("prix vente = "+list[i].benefice()
            );
        }
    }
}

