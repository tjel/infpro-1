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
//public class dbBasics { // dla obiektu bez obsługi wątków
public class dbBasics extends Thread { // dla obiektu z obsługą wątków
    private static Connection c;
    private static Statement stmt;

    /**
     * Konstruktor na potrzeby wątku/obiektu
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
    
    
    /**
    * pusto, bo w sumie to ma tylko trzymac połączenie z konstruktora
    *
    */
    public void run(){
        
    }
    
    /**
     * Metoda wykonująca zapytanie SQL tylko do wyświetlania danych.
     * Ta metoda ma na celu tylko wyświetlić dane z CAŁEJ tabeli. Do wyświetlania pojedyńczych kolumn
     * użyj innej metody.
     *
     * Wprowadzenie komendy typu INSERT albo UPDATE nie będzie funkcjonować
     * @param query wejściowe dowolne zapytanie użytkownika
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
    
    /**
     * Metoda zwracająca cene modułu
     * @param id
     * @return Cena modułu
     * @throws SQLException 
     */
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
     * Metoda dodająca do bazy modułów wpis o minimalnych wymaganiach, tzn. id i nazwe modułu
     * @param id wejściowy parametr dla operacji wstawiania do bazy danych. Klucz główny.
     * @param name wejściowy parametr dla operacji wstawiania do bazy danych. Nazwa osiągnięcia
     * @throws SQLException
     */
    public static void dbInsert(int id,String name) throws SQLException{
        String query = "INSERT INTO modules (id,name) VALUES (" + id + ", '" + name + "');";
        stmt.executeUpdate(query);
        c.commit();
        System.out.println("Insert successful.");
    }
    
    /**
     * Metoda dodająca do bazy modułów kompletny wpis
     * @param id
     * @param name
     * @param curr_lvl
     * @param cost
     * @param points
     * @param available Wartość bool -> 1/0
     * @param active Wartość bool -> 1/0
     * @param owned Wartość bool -> 1/0
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
     * Metoda dodająca do bazy danych tylko znaczące pola przy tworzeniu modułu
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
     * Metoda aktualizująca cene modułu
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
     * Metoda aktualizująca status posiadania modułu
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
     * Metoda zdobywająca ilość poprawnie rozwiązanych zadań
     * @param id
     * @return Zwraca wartość kolumny successful dla konkretnego id 
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
     * Metoda ustalająca ilość poprawnie rozwiązanych zadań //private
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
     * Metoda zwiększająca wartość w tabeli successful o 1
     * @param id 
     */
    public static void dbEarnedSuccessful(int id){
        int successful = dbGetSuccessful(id);
        successful++;
        dbUpdateSuccessful(id,successful);
    }
    
    
    
    /**
     * Metoda zdobywająca ilość błednie rozwiązanych zadań
     * @param id
     * @return Zwraca wartość kolumny unsuccessful dla konkretnego id 
     */
    public static int dbGetUnsuccessful(int id){
        int unsuccessful = 0;
        try {
            String query = "SELECT unsuccessful FROM modules WHERE id ="+ id + ";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){   unsuccessful = rs.getInt("unsuccessful");   }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error while getting Unuccessfull.");
        }
        return unsuccessful;
    }
    
    /**
     * Metoda ustalająca ilość błednie rozwiązanych zadań //private
     * @param id
     * @param unsuccessful 
     */
    private static void dbUpdateUnsuccessful(int id, int unsuccessful) { 
        try {
            String query = "UPDATE modules SET unsuccessful = " + unsuccessful + " WHERE id = " + id + ";";
            stmt.executeUpdate(query);
            c.commit();
            System.out.println("Update 'UNSUCCESSFUL' value successful.");
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Update 'UNSUCCESSFUL' value unsuccessful.");
        }
    }
    
    /**
     * Metoda zwiększająca wartość w tabeli unsuccessful o 1
     * @param id 
     */
    public static void dbEarnedUnsuccessful(int id){
        int unsuccessful = dbGetUnsuccessful(id);
        unsuccessful++;
        dbUpdateSuccessful(id,unsuccessful);
    }
    
}
