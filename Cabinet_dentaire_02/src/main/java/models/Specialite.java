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
public class Specialite extends BaseModel{
    private String specialite;
    private Double salaireHeure;

    public Specialite(){
        
    }
    public Specialite(String specialite, double salaireHeure) {
        this.specialite = specialite;
        this.salaireHeure = salaireHeure;
    }

    public Specialite(String specialite, double salaireHeure, int id) {
        super(id);
        this.specialite = specialite;
        this.salaireHeure = salaireHeure;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Double getSalaireHeure() {
        return salaireHeure;
    }

    public void setSalaireHeure(Double salaireHeure) {
        this.salaireHeure = salaireHeure;
    }

    // SELECT EMPLOYE
    public Specialite[] selectSpecialite(Connection conn) throws Exception{
        Specialite[] di =(Specialite[]) selection(this, conn).toArray(new Specialite[1]);
        return di;
    }

    public Specialite[] selectSpecialite() throws Exception{
        Specialite[] di =(Specialite[]) selection(this).toArray(new Specialite[1]);
        return di;
    }
    public static void main(String[] args) throws Exception {
        //Utility u = new Utility();
        Specialite sp = new Specialite();
        Specialite[] list = sp.selectSpecialite();

        //Specialite specialite = new Specialite();
        //System.out.println(specialite.salaireHeure == 0.0);
        //Specialite[] spe = specialite.selectSpecialite();
        for(int i = 0; i < list.length; i++){
            System.out.println("nom = "+list[i].getSpecialite());
        }
    }
}
