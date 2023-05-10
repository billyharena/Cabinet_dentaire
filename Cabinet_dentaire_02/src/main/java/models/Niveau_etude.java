/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.Utility;

import java.sql.Connection;

/**
 *
 * @author billy
 */
public class Niveau_etude extends BaseModel{
    private String niveau_etude;

    public Niveau_etude() {
    }

    public Niveau_etude(String niveau_etude) {
        this.niveau_etude = niveau_etude;
    }

    public Niveau_etude(String niveau_etude, Integer id) {
        super(id);
        this.niveau_etude = niveau_etude;
    }

    public String getNiveau_etude() {
        return niveau_etude;
    }

    public void setNiveau_etude(String niveau_etude) {
        this.niveau_etude = niveau_etude;
    }
    // SELECT DIPLOME
    public Niveau_etude[] selectDiplome(Connection conn) throws Exception{
        Niveau_etude[] di =(Niveau_etude[]) selection(this, conn).toArray(new Niveau_etude[1]);
        return di;
    }

    // SELECT DIPLOME WITH PARAMETERS
    public Niveau_etude[] selectDiplome() throws Exception{
        Niveau_etude[] di =(Niveau_etude[]) selection(this).toArray(new Niveau_etude[1]);
        return di;
    }
    /*public static void main(String[] args) throws Exception {
        Utility u = new Utility();
        Niveau_etude [] ne = new Niveau_etude().selectDiplome();
        for (int i = 0; i < ne.length; i++) {
            System.out.println("ne = "+ne[i].getNiveau_etude());
        }
    }*/
}
