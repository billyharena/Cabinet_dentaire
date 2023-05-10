/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import function.Function;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author billy
 */
public class Employe extends BaseModel{
    private String nom;
    private String prenom;
    private Date date_naissance;
    private Integer idgenre;
    private Integer idniveau_etude;

    public Employe() {
    }

    public Employe(String nom, String prenom, Date date_naissance, Integer idgenre, Integer idniveau_etude) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.idgenre = idgenre;
        this.idniveau_etude = idniveau_etude;
    }

    public Employe(String nom, String prenom, Date date_naissance, Integer idgenre, Integer idniveau_etude, Integer id) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.idgenre = idgenre;
        this.idniveau_etude = idniveau_etude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance)throws Exception {
        Date maintenant = Date.valueOf(LocalDate.now());
        long diff = Function.getYear(maintenant, date_naissance);
        if(diff < 18) throw new Exception("Vous n'êtes pas encore majeur!");
        else this.date_naissance = date_naissance;
    }

    public int getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(Integer idgenre) {
        this.idgenre = idgenre;
    }

    public int getIdniveau_etude() {
        return idniveau_etude;
    }

    public void setIdniveau_etude(Integer idniveau_etude) {
        this.idniveau_etude = idniveau_etude;
    }
    
    // GET ID OF THE LAST EMPLOYE

    public Integer getIdLastEmploye(Connection connection) throws Exception{
        Integer id = 0;
        id = getIdLast(this, connection);
        return id;
    }
    public Integer getIdLastEmploye() throws Exception{
        Integer id = 0;
        id = getIdLast(this);
        return id;
    }
    // INSERTION AVEC CONNEXION
    public void insererEmploye(Connection conn) throws Exception{
        inserer(this, conn);
        System.out.println("Insertion employé réussi !");
    }

    // INSERTION SANS CONNEXION ET FERMER
    public void insererEmploye() throws Exception{
        inserer(this);
    }
    // SELECT EMPLOYE
    public Employe[] selectEmploye(Connection conn) throws Exception{
        Employe[] di =(Employe[]) selection(this, conn).toArray(new Employe[1]);
        return di;
    }

    public Employe[] selectEmploye() throws Exception{
        Employe[] di =(Employe[]) selection(this).toArray(new Employe[1]);
        return di;
    }

    public int getIdEmploye() throws Exception{
        int id = 0;
        id = getIdObject(this);
        return id;
    }

    // INSERTION RETOURNANT L'ID
    public int insertEmployeId() throws Exception{
        int id = 0;
        id = insertGetId(this);
        return id;
    }
}
