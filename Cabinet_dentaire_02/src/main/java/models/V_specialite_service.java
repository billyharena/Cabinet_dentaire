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

public class V_specialite_service {
    private Integer idservice;
    private String service;
    private Double duree;
    private Double prix;
    private Integer idspecialite;
    private String specialite;
    private Double salaireheure;
    private Double heuretravail;
    private Double margebenef;

    public V_specialite_service(){

    }

    public V_specialite_service(Integer idservice, String service, Double duree, Double prix, Integer idspecialite, String specialite, Double salaireheure, Double heuretravail, Double margebenef) {
        this.idservice = idservice;
        this.service = service;
        this.duree = duree;
        this.prix = prix;
        this.idspecialite = idspecialite;
        this.specialite = specialite;
        this.salaireheure = salaireheure;
        this.heuretravail = heuretravail;
        this.margebenef = margebenef;
    }

    public Integer getIdservice() {
        return idservice;
    }

    public void setIdservice(Integer idservice) {
        this.idservice = idservice;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getIdspecialite() {
        return idspecialite;
    }

    public void setIdspecialite(Integer idspecialite) {
        this.idspecialite = idspecialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Double getSalaireheure() {
        return salaireheure;
    }

    public void setSalaireheure(Double salaireheure) {
        this.salaireheure = salaireheure;
    }

    public Double getHeuretravail() {
        return heuretravail;
    }

    public void setHeuretravail(Double heuretravail) {
        this.heuretravail = heuretravail;
    }

    public Double getMargebenef() {
        return margebenef;
    }

    public void setMargebenef(Double margebenef) {
        this.margebenef = margebenef;
    }

    // SELECTION AVEC CONNEXION SANS FERMER LA CONNEXION
    public V_specialite_service[] selection(V_specialite_service vue, Connection conn) throws Exception{
        Utility u = new Utility();
        List ob = u.selectObjects(vue, conn);
        V_specialite_service[] valeur =(V_specialite_service[]) ob.toArray(new V_specialite_service[1]);
        return valeur;
    }

    // SELECTION EN FERMANT LA CONNEXION
    public V_specialite_service[] selection(V_specialite_service vue) throws Exception{
        Utility u = new Utility();
        List ob = u.selectObjects(vue);
        V_specialite_service[] valeur =(V_specialite_service[]) ob.toArray(new V_specialite_service[1]);
        return valeur;
    }
    // SELECTION AVEC CONNEXION SANS FERMER LA CONNEXION
    public V_specialite_service[] selectionAvance(V_specialite_service vue, Connection conn) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(vue, conn);
        V_specialite_service[] valeur =(V_specialite_service[]) ob.toArray(new V_specialite_service[1]);
        return valeur;
    }

    // SELECTION EN FERMANT LA CONNEXION
    public V_specialite_service[] selectionAvance(V_specialite_service vue) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(vue);
        V_specialite_service[] valeur =(V_specialite_service[]) ob.toArray(new V_specialite_service[1]);
        return valeur;
    }

    public static Double revientsalarialparservice(Integer idservice, Connection conn) throws Exception{
        Double revientmateriel = 0.00;
        String sql = "SELECT SUM(salaireHeure * heureTravail) AS revientsalarial FROM v_specialite_service WHERE idservice = "+idservice;
        Statement stat = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            stat = conn.createStatement();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                revientmateriel = rs.getDouble("revientsalarial");
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
    public static Double revientsalarialparservice(Integer idservice) throws Exception{
        Double prix = 0.00;
        String user = "mme_baovola";
        String databasename = "cabinet_dentaire";
        String password = "baovola";
        String port = "5432";
        Connection conn = new DatabaseConnexion().getPostgreSQLConnexion(user,databasename,password, port);
        prix = revientsalarialparservice(idservice, conn);
        conn.close();
        if (conn.isClosed()) System.out.println("Connection fermée !!");
        else System.out.println("Connection en cours !!");
        return prix;
    }

    public static Double[] listRevientSalarial(Connection connection) throws Exception{
        ArrayList<Double> resultat = new ArrayList<>();
        String sql = "SELECT idservice, idservice, SUM(salaireHeure * heureTravail) AS revientsalarial FROM v_specialite_service GROUP BY idservice ORDER BY idservice ASC";
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = connection.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()){
                resultat.add(rs.getDouble("revientsalarial"));
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

    public static Double[] listRevientSalarial() throws Exception{
        Double[] list = null;
        String user = "mme_baovola";
        String databasename = "cabinet_dentaire";
        String password = "baovola";
        String port = "5432";
        Connection conn = new DatabaseConnexion().getPostgreSQLConnexion(user,databasename,password, port);
        list = listRevientSalarial(conn);
        conn.close();
        if (conn.isClosed()) System.out.println("Connection fermée !!");
        else System.out.println("Connection en cours !!");
        return list;
    }
    public static Double sommerevientsalarial(Connection connection) throws Exception{
        Double somme = 0.00;
        Double[] revient = listRevientSalarial();
        somme = Function.sumDoubleArray(revient);
        return somme;
    }
    public static Double sommerevientsalarial() throws Exception{
        Double somme = 0.00;
        String user = "mme_baovola";
        String databasename = "cabinet_dentaire";
        String password = "baovola";
        String port = "5432";
        Connection conn = new DatabaseConnexion().getPostgreSQLConnexion(user,databasename,password, port);
        somme = sommerevientsalarial(conn);
        conn.close();
        if (conn.isClosed()) System.out.println("Connection fermée !!");
        else System.out.println("Connection en cours !!");
        return somme;
    }

    /*public static void main(String[] args) throws Exception {
        Double somme = revientsalarialparservice(1);
        System.out.println("somme = "+ somme);
    }*/
}
