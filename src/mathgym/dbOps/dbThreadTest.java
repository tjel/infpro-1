/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym.dbOps;

import java.sql.SQLException;

/**
 *
 * @author zajec_000
 */
public class dbThreadTest {
    
    /**
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        dbBasics dbConnection = new dbBasics(); //sposób na obiekt
        //new dbBasics().start(); //sposób na wątki
        //dbBasics.dbSelect("SELECT * FROM modules");
        //System.out.println(dbBasics.dbSelectCost(2));
        
        //dbBasics.dbUpdadeCost(2, 600);
        //dbBasics.dbSelect("SELECT * FROM modules");
        //System.out.println(dbBasics.dbSelectCost(2));
        
        //dbCreate.Create_DB();
        //dbBasics.dbUpdateOwned(2,0);
        
        //System.out.println(dbBasics.dbGetSuccessful(2));
        //dbBasics.dbEarnedSuccessful(2);
        
        dbBasics.dbInsert(3,"Dodawanie Wielokrotne", 700);
        dbBasics.dbSelect("SELECT * FROM modules");
        
        //System.out.println(dbBasics.dbGetSuccessful(2));
        
        //dbBasics.dbSelect("SELECT * FROM modules");
        
        
        
        
    }
}
