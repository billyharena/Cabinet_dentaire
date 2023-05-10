package models;

import database.DatabaseConnexion;
import database.Utility;
import function.Function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class V_produit_service {
    private Integer idproduit;
    private String nomproduit;
    private Double prixunitaire;
    private Integer idserviceproduit;
    private Integer quantiteproduit;
    private Integer idservice;
    private String nomservice;

    public V_produit_service(){

    }
    public V_produit_service(Integer idproduit, String nomproduit, Double prixunitaire, Integer idserviceproduit, Integer quantiteproduit, Integer idservice, String nomservice) {
        this.idproduit = idproduit;
        this.nomproduit = nomproduit;
        this.prixunitaire = prixunitaire;
        this.idserviceproduit = idserviceproduit;
        this.quantiteproduit = quantiteproduit;
        this.idservice = idservice;
        this.nomservice = nomservice;
    }

    public Integer getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(Integer idproduit) {
        this.idproduit = idproduit;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public Double getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(Double prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public Integer getIdserviceproduit() {
        return idserviceproduit;
    }

    public void setIdserviceproduit(Integer idserviceproduit) {
        this.idserviceproduit = idserviceproduit;
    }

    public Integer getQuantiteproduit() {
        return quantiteproduit;
    }

    public void setQuantiteproduit(Integer quantiteproduit) {
        this.quantiteproduit = quantiteproduit;
    }

    public Integer getIdservice() {
        return idservice;
    }

    public void setIdservice(Integer idservice) {
        this.idservice = idservice;
    }

    public String getNomservice() {
        return nomservice;
    }

    public void setNomservice(String nomservice) {
        this.nomservice = nomservice;
    }
    // SELECTION AVEC CONNEXION SANS FERMER LA CONNEXION
    public V_produit_service[] selection(V_produit_service vue, Connection conn) throws Exception{
        Utility u = new Utility();
        List ob = u.selectObjects(vue, conn);
        V_produit_service[] valeur =(V_produit_service[]) ob.toArray(new V_produit_service[1]);
        return valeur;
    }

    // SELECTION EN FERMANT LA CONNEXION
    public V_produit_service[] selection(V_produit_service vue) throws Exception{
        Utility u = new Utility();
        List ob = u.selectObjects(vue);
        V_produit_service[] valeur =(V_produit_service[]) ob.toArray(new V_produit_service[1]);
        return valeur;
    }
    // SELECTION AVEC CONNEXION SANS FERMER LA CONNEXION
    public V_produit_service[] selectionAvance(V_produit_service vue, Connection conn) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(vue, conn);
        V_produit_service[] valeur =(V_produit_service[]) ob.toArray(new V_produit_service[1]);
        return valeur;
    }

    // SELECTION EN FERMANT LA CONNEXION
    public V_produit_service[] selectionAvance(V_produit_service vue) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(vue);
        V_produit_service[] valeur =(V_produit_service[]) ob.toArray(new V_produit_service[1]);
        return valeur;
    }

    public static Double revientmaterielparservice(Integer idservice, Connection conn) throws Exception{
        Double revientmateriel = 0.00;
        String sql = "SELECT SUM(prixUnitaire * quantiteProduit) AS revientMateriel FROM v_produit_service WHERE idservice = "+idservice;
        Statement stat = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            stat = conn.createStatement();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                revientmateriel = rs.getDouble("revientmateriel");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            conn.close();
            if (conn.isClosed()) System.out.println("Connection fermée dû à une exception !");
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (stat != null) stat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return revientmateriel;
    }
    public static Double revientmaterielparservice(Integer idservice) throws Exception{
        Double prix = 0.00;
        String user = "mme_baovola";
        String databasename = "cabinet_dentaire";
        String password = "baovola";
        String port = "5432";
        Connection conn = new DatabaseConnexion().getPostgreSQLConnexion(user,databasename,password, port);
        prix = revientmaterielparservice(idservice, conn);
        conn.close();
        if (conn.isClosed()) System.out.println("Connection fermée !!");
        else System.out.println("Connection en cours !!");
        return prix;
    }

    public static Double[] listRevientMateriel(Connection connection) throws Exception{
        ArrayList<Double> resultat = new ArrayList<>();
        String sql = "SELECT idservice, SUM(prixUnitaire * quantiteProduit) AS revientMateriel FROM v_produit_service GROUP BY idservice ORDER BY idservice ASC";
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = connection.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()){
                resultat.add(rs.getDouble("revientmateriel"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            connection.close();
            if (connection.isClosed()) System.out.println("Connection fermée dû à une exception !");
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stat != null) stat.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultat.toArray(new Double[resultat.size()]);
    }

    public static Double[] listRevientMateriel() throws Exception{
        Double[] list = null;
        String user = "mme_baovola";
        String databasename = "cabinet_dentaire";
        String password = "baovola";
        String port = "5432";
        Connection conn = new DatabaseConnexion().getPostgreSQLConnexion(user,databasename,password, port);
        list = listRevientMateriel(conn);
        conn.close();
        if (conn.isClosed()) System.out.println("Connection fermée !!");
        else System.out.println("Connection en cours !!");
        return list;
    }
    public static Double sommerevientmateriel(Connection connection) throws Exception{
        Double somme = 0.00;
        Double[] revient = listRevientMateriel();
        somme = Function.sumDoubleArray(revient);
        return somme;
    }
    public static Double sommerevientmateriel() throws Exception{
        Double somme = 0.00;
        String user = "mme_baovola";
        String databasename = "cabinet_dentaire";
        String password = "baovola";
        String port = "5432";
        Connection conn = new DatabaseConnexion().getPostgreSQLConnexion(user,databasename,password, port);
        somme = sommerevientmateriel(conn);
        conn.close();
        if (conn.isClosed()) System.out.println("Connection fermée !!");
        else System.out.println("Connection en cours !!");
        return somme;
    }

    /*public static void main(String[] args) throws Exception {
        Double prix = sommerevientmateriel();
        System.out.println("somme = "+prix);
    }*/
}
