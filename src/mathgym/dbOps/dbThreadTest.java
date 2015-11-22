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
        new dbBasics().start();
        //dbBasics.dbSelect("SELECT * FROM modules");
        //dbBasics.dbInsertMinimal(1,"Dodawanie");
        
        //dbBasics.dbSelect("SELECT * FROM modules");
        //dbBasics.dbInsertMinimal(2, "Odejmowanie");
        //dbBasics.dbSelect("SELECT * FROM modules");
        
        //System.out.println(dbBasics.dbSelectCost(2));
        
        //dbBasics.dbUpdadeCost(2, 600);
        dbBasics.dbSelect("SELECT * FROM modules");
        System.out.println(dbBasics.dbSelectCost(2));
    }
}
