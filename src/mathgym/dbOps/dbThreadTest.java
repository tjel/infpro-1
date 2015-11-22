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
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        new dbBasics().start();
        dbBasics.dbSelect("SELECT * FROM modules");
        
    }
}
