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
public class dbCreate {

    /**
     *  metoda tworz¹ca baze danych w razie wu. :)
     *  w przypadku zepsucia bazy aby wygenerowaæ now¹ wystarczy wywo³aæ funkcje, najpierw trzeba usun¹æ star¹ baze
     */
    public static void Create_DB(){
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:modules.db");
            stmt = c.createStatement();
            String query = "CREATE TABLE modules" +
                    "(id INTEGER NOT NULL," +
                    "name TEXT," +
                    "curr_lvl INTEGER DEFAULT 0," +
                    "cost INTEGER DEFAULT 0," +
                    "points INTEGER DEFAULT 0," +
                    "available INTEGER DEFAULT 0," +
                    "active INTEGER DEFAULT 0," +
                    "owned INTEGER DEFAULT 0," +
                    "successful INTEGER DEFAULT 0," +
                    "unsuccessful INTEGER DEFAULT 0," +
                    "PRIMARY KEY (id))";
            
            stmt.executeUpdate(query);
            stmt.close();
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Table created!");
        
    }
}
