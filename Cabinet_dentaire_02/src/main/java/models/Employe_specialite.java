package models;

import java.sql.Connection;

public class Employe_specialite extends BaseModel{
    private Integer idemploye;
    private Integer idspecialite;

    public Employe_specialite(){

    }
    public Employe_specialite(Integer idemploye, Integer idspecialite) {
        this.idemploye = idemploye;
        this.idspecialite = idspecialite;
    }

    public Employe_specialite(Integer id, Integer idemploye, Integer idspecialite) {
        super(id);
        this.idemploye = idemploye;
        this.idspecialite = idspecialite;
    }

    public Integer getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(Integer idemploye) {
        this.idemploye = idemploye;
    }

    public Integer getIdspecialite() {
        return idspecialite;
    }

    public void setIdspecialite(Integer idspecialite) {
        this.idspecialite = idspecialite;
    }
    // INSERTION AVEC CONNEXION
    public void insererEmployeSpecialite(Connection conn) throws Exception{
        inserer(this, conn);
        System.out.println("Insertion employé réussi !");
    }

    // INSERTION SANS CONNEXION ET FERMER
    public void insererEmployeSpecialite() throws Exception{
        inserer(this);
    }
    // SELECT EMPLOYE
    public Employe_specialite[] selectEmployeService(Connection conn) throws Exception{
        Employe_specialite[] di =(Employe_specialite[]) selection(this, conn).toArray(new Employe_specialite[1]);
        return di;
    }

    public Employe_specialite[] selectEmployeService() throws Exception{
        Employe_specialite[] di =(Employe_specialite[]) selection(this).toArray(new Employe_specialite[1]);
        return di;
    }

    public int getIdEmployeservice() throws Exception{
        int id = 0;
        id = getIdObject(this);
        return id;
    }

    // INSERTION RETOURNANT L'ID
    public int insertEmployeserviceId() throws Exception{
        int id = 0;
        id = insertGetId(this);
        return id;
    }
    // SELECT AVANCEE
    public Employe_specialite[] selectEmployeserviceAvance(Connection conn) throws Exception{
        Employe_specialite[] di =(Employe_specialite[]) selectionAdvanced(this, conn).toArray(new Employe_specialite[1]);
        return di;
    }

    public Employe_specialite[] selectEmployeserviceAvance(Employe_specialite service) throws Exception{
        Employe_specialite[] di =(Employe_specialite[]) selectionAdvanced(service).toArray(new Employe_specialite[1]);
        return di;
    }
}
