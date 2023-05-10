/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import database.Utility;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author billy
 */
public class BaseModel {
    private Integer id;

    public BaseModel() {
    }

    public BaseModel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    // INSERTION AVEC CONNEXION SANS FERMER LA CONNEXION
    public void inserer(BaseModel bm, Connection conn) throws Exception{
        Utility u = new Utility();
        u.insertObject(bm, conn);
    }
    
    // INSERTION EN FERMANT LA CONNEXION
    public void inserer(BaseModel bm) throws Exception{
        Utility u = new Utility();
        u.insertObject(bm);
    }
    
    // SELECTION AVEC CONNEXION SANS FERMER LA CONNEXION
    public List selection(BaseModel bm, Connection conn) throws Exception{
        Utility u = new Utility();
        List ob = u.selectObjects(bm, conn);
        return ob;
    }
    
    // SELECTION EN FERMANT LA CONNEXION
    public List selection(BaseModel bm) throws Exception{
        Utility u = new Utility();
        List ob = u.selectObjects(bm);
        return ob;
    }
    
    // UPDATE AVEC CONNEXION SANS FERMER LA CONNEXION
    public void modification(BaseModel bm, Connection conn) throws Exception{
        Utility u = new Utility();
        u.updateObject(bm, id, conn);
    }
    
    // UPDATE EN FERMANT LA CONNEXION
    public void modification(BaseModel bm) throws Exception{
        Utility u = new Utility();
        u.updateObject(bm, id);
    }
    
    // INSERTION EN RETOURNANT L'ID DU DERNIER ELEMENT INSERER AVEC CONNEXION
    public int insertionIdLastElement(BaseModel bm, Connection conn) throws Exception{
        int id = 0;
        Utility u = new Utility();
        id = u.InsertionWithIdOfLastElement(bm, conn);
        return id;
    }
    
    // INSERTION EN RETOURNANT L'ID DU DERNIER ELEMENT INSERER EN FERMANT CONNEXION
    public int insertionIdLastElement(BaseModel bm) throws Exception{
        int id = 0;
        Utility u = new Utility();
        id = u.InsertionWithIdOfLastElement(bm);
        return id;
    }
    
    // GET ID D'UN OBJET AVEC CONNEXION
    public int getIdObject(BaseModel bm, Connection conn) throws Exception{
        int id = 0;
        Utility u = new Utility();
        id = u.getId(bm, conn);
        return id;
    }
    
    // GET ID D'UN OBJET EN FERMANT LA CONNEXION
    public int getIdObject(BaseModel bm) throws Exception{
        int id = 0;
        Utility u = new Utility();
        id = u.getId(bm);
        System.out.println("idbase "+id);
        return id;
    }
    
    // INSERTION RETOURNANT L'ID AVEC CONNEXION
    public int insertGetId(BaseModel bm, Connection conn) throws Exception{
        int id = 0;
        Utility u = new Utility();
        id = u.InsertionWithIdOfLastElement(bm, conn);
        return id;
    }
    
    // INSERTION RETOURNANT L'ID EN FERMANT LA CONNEXION
    public int insertGetId(BaseModel bm) throws Exception{
        int id = 0;
        Utility u = new Utility();
        id = u.InsertionWithIdOfLastElement(bm);
        return id;
    }

    // ID DU DERNIER ELEMENT DE LA TABLE
    public Integer getIdLast(BaseModel baseModel, Connection connection) throws Exception{
        Integer id = 0;
        Utility u = new Utility();
        id = u.getIdOfLastElement(baseModel, connection);
        return id;
    }

    public Integer getIdLast(BaseModel baseModel) throws Exception{
        Integer id = 0;
        Utility utility = new Utility();
        id = utility.getIdOfLastElement(baseModel);
        return id;
    }
    // SELECTION AVEC CONNEXION SANS FERMER LA CONNEXION
    public List selectionAdvanced(BaseModel bm, Connection conn) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(bm, conn);
        return ob;
    }

    // SELECTION EN FERMANT LA CONNEXION
    public List selectionAdvanced(BaseModel bm) throws Exception{
        Utility u = new Utility();
        List ob = u.advancedSearch(bm);
        return ob;
    }
}
