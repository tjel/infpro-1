/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym.dbOps;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *  
 * @author zajec_000
 */
public class dbBasics {
//public class dbBasics extends Thread {
    private static Connection c;
    private static Statement stmt;

    /**
     * Konstruktor na potrzeby w�tku
     * @throws ClassNotFoundException
     */
    public dbBasics() throws ClassNotFoundException{
       Class.forName("org.sqlite.JDBC");
        try {
            this.c = DriverManager.getConnection("jdbc:sqlite:modules.db");
            this.c.setAutoCommit(false);
            this.stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    * pusto bo w sumie to ma tylko trzymac po��czenie z konstruktora
    
    */
    //public void run(){
        
    //}
    
    /**
     *
     * @param query wej�ciowe dowolne zapytanie u�ytkownika
     * @throws SQLException
     */
    public static void dbSelect(String query) throws SQLException{
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int curr_lvl = rs.getInt("curr_lvl");
            int cost = rs.getInt("cost");
            int points = rs.getInt("points");
            int available = rs.getInt("available");
            int active = rs.getInt("active");
            int owned = rs.getInt("owned");
            int successful = rs.getInt("successful");
            int unsuccessful = rs.getInt("unsuccessful");
            System.out.println(id + "\t" + name +
                    "\t" + curr_lvl + 
                    "\t" + cost + 
                    "\t" + points + 
                    "\t" + available + 
                    "\t" + active + 
                    "\t" + owned + 
                    "\t" + successful + 
                    "\t" + unsuccessful 
            );
        }
        
        rs.close();
        System.out.println("Operation successful");
    }
    
    public static int dbSelectCost(int id) throws SQLException{
        String query = "SELECT cost FROM modules WHERE id ="+ id + ";";
        ResultSet rs = stmt.executeQuery(query);
        int cost = 0;
        while(rs.next()){
        cost = rs.getInt("cost");
        }
        rs.close();
        return cost;
    }
    
    /**
     * Metoda dodaj�ca do bazy modu��w wpis o minimalnych wymaganiach, tzn. id i nazwe modu�u
     * @param id wej�ciowy parametr dla operacji wstawiania do bazy danych. Klucz g��wny.
     * @param name wej�ciowy parametr dla operacji wstawiania do bazy danych. Nazwa osi�gni�cia
     * @throws SQLException
     */
    public static void dbInsert(int id,String name) throws SQLException{
        String query = "INSERT INTO modules (id,name) VALUES (" + id + ", '" + name + "');";
        stmt.executeUpdate(query);
        c.commit();
        System.out.println("Insert successful.");
    }
    
    /**
     * Metoda dodaj�ca do bazy modu��w kompletny wpis
     * @param id
     * @param name
     * @param curr_lvl
     * @param cost
     * @param points
     * @param available Warto�c bool -> 1/0
     * @param active Warto�c bool -> 1/0
     * @param owned Warto�c bool -> 1/0
     * @param successful
     * @param unsuccessful
     * @throws SQLException 
     */
    public static void dbInsert(int id,String name, int curr_lvl, int cost, int points,
            int available, int active, int owned, int successful, int unsuccessful) throws SQLException{
        String query = "INSERT INTO modules (id,name, curr_lvl, cost,"
                + "points, available, active, owned,"
                + "successful, unsuccessful) VALUES "
                + "(" + id + ", '" + name + "'"
                + ", " + curr_lvl + ", " + cost
                + ", " + points + ", " + available
                + ", " + active + ", " + owned
                + ", " + successful + ", " + unsuccessful
                + ");";
        stmt.executeUpdate(query);
        c.commit();
        System.out.println("Insert successful.");
    }
    
    /**
     * Metoda dodaj�ca do bazy danych tylko znacz�ce pola przy tworzeniu modu�u
     * @param id
     * @param name
     * @param cost
     * @throws SQLException 
     */
    public static void dbInsert(int id,String name, int cost) throws SQLException{
        String query = "INSERT INTO modules (id, name, cost) VALUES "
                + "(" + id + ", '" + name + "'"
                + ", " + cost
                + ");";
        stmt.executeUpdate(query);
        c.commit();
        System.out.println("Insert successful.");
    }
    
    /**
     * Metoda aktualizuj�ca cene modu�u
     * @param id
     * @param cost
     * @throws SQLException 
     */
    public static void dbUpdadeCost(int id,int cost) throws SQLException{
        String query = "UPDATE modules SET cost = "+ cost +" WHERE id = "+ id + ";";
        stmt.executeUpdate(query);
        c.commit();
        System.out.println("Update successful");
    }
    
    /**
     * Metoda aktualizuj�ca status posiadania modu�u
     * @param id
     * @param owned 
     */
    public static void dbUpdateOwned(int id, int owned) { 
        try {
            String query = "UPDATE modules SET owned = " + owned + " WHERE id = " + id + ";";
            stmt.executeUpdate(query);
            c.commit();
            System.out.println("Update 'OWNED' value successful.");
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Update 'OWNED' value unsuccessful.");
        }
    }
    
    /**
     * Metoda zdobywaj�ca ilo�� poprawnie rozwi�zanych zada�
     * @param id
     * @return Zwraca warto�� kolumny successful dla konkretnego id 
     */
    public static int dbGetSuccessful(int id){
        int successful = 0;
        try {
            String query = "SELECT successful FROM modules WHERE id ="+ id + ";";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                successful = rs.getInt("successful");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error while getting Successfull.");
        }
        return successful;
        
    }
    
    /**
     * Metoda ustalaj�ca ilo�� poprawnie rozwi�zanych zada� //private
     * @param id
     * @param successful 
     */
    private static void dbUpdateSuccessful(int id, int successful) { 
        try {
            String query = "UPDATE modules SET successful = " + successful + " WHERE id = " + id + ";";
            stmt.executeUpdate(query);
            c.commit();
            System.out.println("Update 'SUCCESSFUL' value successful.");
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Update 'SUCCESSFUL' value unsuccessful.");
        }
    }
    
    /**
     * Metoda zwi�kszaj�ca warto�� w tabeli successful o 1
     * @param id 
     */
    public static void dbEarnedSuccessful(int id){
        int successful = dbGetSuccessful(id);
        successful++;
        dbUpdateSuccessful(id,successful);
    }
    
    
}
