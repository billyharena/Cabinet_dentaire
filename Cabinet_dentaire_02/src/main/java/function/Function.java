/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import database.DatabaseConnexion;
import models.Employe;
import models.Service;
import models.Specialite;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author billy
 */
public class Function {
    public static Connection getConnection() throws Exception{
        String user = "mme_baovola";
        String databasename = "cabinet_dentaire";
        String password = "baovola";
        String port = "5432";
        Connection conn = new DatabaseConnexion().getPostgreSQLConnexion(user,databasename,password, port);
        return conn;
    }
    public static long getYear(Date un, Date deux){
        long p = un.getTime() - deux.getTime();
        long i =  p /(24 * 60 * 60 * 1000) / 365;
        System.out.println("age = "+ i);
        return i;
    }
    
    /*public static String getSqlSelection(Object object, String recherche) throws Exception{
        String sql = "";
        Class clazz = object.getClass();
        String tableName = clazz.getSimpleName();
        System.out.println("tablename "+ tableName);
        // Préparez la requête SQL
        sql = "SELECT "+ recherche +" FROM " + tableName;
        Field[] fields = clazz.getDeclaredFields();
        boolean valeurOk = false;
        String value = "";
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getType().isPrimitive() && field.get(object) != null ||
        (field.getType().isPrimitive() &&  !field.get(object).equals(0.0)) ){
                System.out.println("field name = " + field.getName());
                if(!valeurOk){
                    sql += " WHERE ";
                    valeurOk = true;
                }
                sql += field.getName() + " = ? AND ";
                value += field.get(object)+",";
            }
        }
        // Enlevez la dernière virgule
        if(valeurOk){
            sql = sql.substring(0, sql.length() - 4);
            value = value.substring(0, value.length() - 1);
            System.out.println("my sql "+sql);
        }
        return sql;
    }*/
    
    public static String getSqlInsertionWithIdReturn(Object object){
        String sql = "";
        Class clazz = object.getClass();
        String tableName = clazz.getSimpleName();
        sql = "INSERT INTO " + tableName + " (";
            Field[] fields = clazz.getDeclaredFields();
            String value = "";
            for (Field field : fields) {
                field.setAccessible(true);
                sql += field.getName() + ",";
                value += "?,";
            }
            // Enlevez la dernière virgule
            sql = sql.substring(0, sql.length() - 1) + ")";
            value = value.substring(0, value.length() - 1) + ")";
            sql += " VALUES (" + value + " RETURNING id";
        return sql;
    }
    public static String getSqlSelection(Object object, String recherche) throws IllegalAccessException {
        String sql = "SELECT " + recherche+ " FROM " + object.getClass().getSimpleName();
        boolean hasConditions = false;
        List<Field> fields = getAllFields(new ArrayList<>(), object.getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(object) != null) {
                if (!hasConditions) {
                    sql += " WHERE ";
                    hasConditions = true;
                } else {
                    sql += " AND ";
                }
                sql += field.getName() + " = ?";
            }
        }
        return sql;
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

    // Somme d'un tableau de Double
    public static Double sumDoubleArray(Double[] arr) {
        Double sum = 0.00;
        for (Double element : arr) {
            sum += element;
        }
        return sum;
    }


    /*public static void main(String[] args) throws Exception {

        Date deux = Date.valueOf("2002-02-14");
        Employe emp = new Employe();
        emp.setId(1);
        String sql = Function.getSqlSelection(emp, "id, nom");
        System.out.println("sql = " + sql);
    }*/
}
