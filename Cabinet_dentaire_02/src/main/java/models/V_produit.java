package models;

import database.Utility;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

public class V_produit {
    private Integer id ;
    private String nom;
    private Double prixUnitaire;
    private Double marge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Double getMarge() {
        return marge;
    }

    public void setMarge(Double marge) {
        this.marge = marge;
    }

    public V_produit(Integer id,String nom, Double prixUnitaire, Double marge) {
        this.setId(id);
        this.setNom(nom);
        this.setPrixUnitaire(prixUnitaire);
        this.setMarge(marge);
    }
    public V_produit(){}
    public Double benefice()
    {
        Double rep = (this.getMarge()*this.getPrixUnitaire())/100;
        return rep;
    }

    public Double prix_conseiller(){
        Double rep = 0.0;
        rep = this.getPrixUnitaire()+this.benefice();
        return rep;
    }
    public V_produit[] selectionAvance(V_produit vue, Connection conn) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(vue, conn);
        V_produit[] valeur =(V_produit[]) ob.toArray(new V_produit[1]);
        return valeur;
    }

    // SELECTION EN FERMANT LA CONNEXION
    public V_produit[] selectionAvance(V_produit vue) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(vue);
        V_produit[] valeur =(V_produit[]) ob.toArray(new V_produit[1]);
        return valeur;
    }
    public V_produit[] selection(V_produit vue, Connection conn) throws Exception{
        Utility u = new Utility();
        List ob = u.selectObjects(vue, conn);
        V_produit[] valeur =(V_produit[]) ob.toArray(new V_produit[1]);
        return valeur;
    }

    // SELECTION EN FERMANT LA CONNEXION
    public V_produit[] selection(V_produit vue) throws Exception{
        Utility u = new Utility();
        List ob = u.selectObjects(vue);
        V_produit[] valeur =(V_produit[]) ob.toArray(new V_produit[1]);
        return valeur;
    }
    public static void main(String[] args) throws Exception {
        V_produit vp = new V_produit();
        vp.setId(4);
        V_produit[] tab = new V_produit().selectionAvance(vp);
        for (int i = 0; i < tab.length; i++) {
            //System.out.println(tab[i].getNom()+" "+tab[i].benefice()+" "+tab[i].prix_conseiller());
            System.out.println("id = "+ tab[i].getId());
        }
    }
}

