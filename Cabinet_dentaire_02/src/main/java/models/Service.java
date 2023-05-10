package models;

import java.sql.Connection;

public class Service extends BaseModel{
    private String service;
    private Double duree;
    private Double prix;
    private Double margebenef;
    public Service(){

    }
    public Service(String service, Double duree, Double prix, Double margebenef) {
        this.service = service;
        this.duree = duree;
        this.prix = prix;
        this.margebenef = margebenef;
    }

    public Service(Integer id, String service, Double duree, Double prix, Double margebenef) {
        super(id);
        this.service = service;
        this.duree = duree;
        this.prix = prix;
        this.margebenef = margebenef;
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

    public Double getMargebenef() {
        return margebenef;
    }

    public void setMargebenef(Double margebenef) {
        this.margebenef = margebenef;
    }

    // INSERTION AVEC CONNEXION
    public void insererService(Connection conn) throws Exception{
        inserer(this, conn);
        System.out.println("Insertion employé réussi !");
    }

    // INSERTION SANS CONNEXION ET FERMER
    public void insererService() throws Exception{
        inserer(this);
    }
    // SELECT EMPLOYE
    public Service[] selectService(Connection conn) throws Exception{
        Service[] di =(Service[]) selection(this, conn).toArray(new Service[1]);
        return di;
    }

    public Service[] selectService() throws Exception{
        Service[] di =(Service[]) selection(this).toArray(new Service[1]);
        return di;
    }

    public int getIdService() throws Exception{
        int id = 0;
        id = getIdObject(this);
        return id;
    }

    // INSERTION RETOURNANT L'ID
    public int insertServiceId() throws Exception{
        int id = 0;
        id = insertGetId(this);
        return id;
    }
    // SELECT AVANCEE
    public Service[] selectServiceAvance(Connection conn) throws Exception{
        Service[] di =(Service[]) selectionAdvanced(this, conn).toArray(new Service[1]);
        return di;
    }

    public Service[] selectServiceAvance(Service service) throws Exception{
        Service[] di =(Service[]) selectionAdvanced(service).toArray(new Service[1]);
        return di;
    }
    /*public static void main(String[] args) throws Exception {
        Service ser = new Service();
        ser.setId(1);
        Service[] list = new Service().selectServiceAvance(ser);
        int t = list.length;
        for (int i = 0; i < t; i++){
            System.out.println("id service = "+ list[i].getId());
            System.out.println("nom service = "+list[i].service);
        }
    }*/
}
