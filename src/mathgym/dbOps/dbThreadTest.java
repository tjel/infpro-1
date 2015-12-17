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
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        //dbBasics dbConnection = new dbBasics(); //sposób na obiekt
        new dbBasics().start(); //sposób na wątki
        //Thread.sleep(1000000);
        //dbBasics.dbSelect("SELECT * FROM modules");
        //System.out.println(dbBasics.dbSelectCost(2));

        //dbBasics.dbUpdadeCost(2, 600);
        //dbBasics.dbSelect("SELECT * FROM modules");
        //System.out.println(dbBasics.dbSelectCost(2));
        //dbCreate.Create_DB();
        //dbBasics.dbUpdateOwned(2,0);
        //System.out.println(dbBasics.dbGetValue(2, "cost"));
        //System.out.println(dbBasics.dbGetSuccessful(2));
        //dbBasics.dbEarnedSuccessful(2);
        //dbBasics.dbInsert(3,"Dodawanie Wielokrotne", 700);
        //dbBasics.dbSelect("SELECT * FROM modules");
        //System.out.println(dbBasics.dbGetSuccessful(2));
        dbBasics.dbSelect("SELECT * FROM modules");

        //dbSelectTimeTest(1000);
        dbGetTimeTest(1000);
        dbSetTimeTest(10);
        
        Thread.sleep(2001);
        dbSetTimeTest(10);

    }

    /**
     * Metoda testująca szybkość wykonania zapytania.
     *
     * @param d ilość zapytań
     * @throws SQLException
     */
    public static void dbSelectTimeTest(int d) throws SQLException {
        int sum = 0;
        for (int i = 0; i < d; i++) {
            long startTime = System.nanoTime();
            dbBasics.dbSelect("SELECT * FROM modules WHERE cost > 650;");
            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.
            //System.out.println(duration + " microseconds");
            sum += duration;
        }
        System.out.println("Average Select time: " + sum / d + " microseconds.");
    }

    public static void dbGetTimeTest(int d) {
        int get = 0;
        int sum = 0;
        for (int i = 0; i < d; i++) {
            long startTime = System.nanoTime();
            get = dbBasics.dbGetCost(1);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.
            //System.out.println(duration + " microseconds");
            sum += duration;
        }
        System.out.println("Average Get time: " + sum / d + " microseconds.");
    }

    public static void dbSetTimeTest(int d) {
        int set = 1;
        int sum = 0;
        for (int i = 0; i < d; i++) {
            long startTime = System.nanoTime();
            dbBasics.dbSetCost(1, 500);
            //dbBasics.dbSetActive(1, 1);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.
            //System.out.println(duration + " microseconds");
            sum += duration;
        }
        System.out.println("Average Set time: " + sum / d + " microseconds.");
    }
}
