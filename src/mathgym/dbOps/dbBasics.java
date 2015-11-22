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
public class dbBasics extends Thread {
    private static Connection c;
    private static Statement stmt;

    /**
     * Konstruktor na potrzeby w¹tku
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
    
    public void run(){
        
    }
    
    /**
     *
     * @param query wejœciowe dowolne zapytanie u¿ytkownika
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
     *
     * @param id wejœciowy parametr dla operacji wstawiania do bazy danych. Klucz g³ówny.
     * @param name wejœciowy parametr dla operacji wstawiania do bazy danych. Nazwa osi¹gniêcia
     * @throws SQLException
     */
    public static void dbInsert(int id,String name) throws SQLException{
        String query = "INSERT INTO modules (id,name) VALUES (" + id + ", '" + name + "')";
        stmt.executeUpdate(query);
        System.out.println("Insert successful.");
    }
}
