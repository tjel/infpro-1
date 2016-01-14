/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym.dbOps;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import mathgym.Modul;

/**
 * 
 * @author zajec_000
 */
//public class dbBasics { // dla obiektu bez obsługi wątków
public class dbBasics extends Thread { // dla obiektu z obsługą wątków

    /**
     * Element typu Connection odpowiadający za połączenie z bazą danych
     */
    private static Connection c;

    /**
     * Element typu Statement odpowiadający za zapytania SQL
     */
    private static Statement stmt;

    /**
     * Zmienna typu int odpowiadająca za zliczanie ilości zapytań na bazie danych. 
     * Wywoływać przy seriach zapytań!
     */
    private static int untilCommitCount;

    /**
     * Zmienna typu long zapisująca czas ostatniej operacji na bazie danych
     */
    private static long lastCommitTime;

    /**
     * Konstruktor na potrzeby wątku/obiektu
     *
     * @throws ClassNotFoundException
     */
    public dbBasics() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            dbBasics.c = DriverManager.getConnection("jdbc:sqlite:modules.db");
            dbBasics.c.setAutoCommit(false);
            dbBasics.stmt = c.createStatement();
            dbBasics.untilCommitCount = 1;
            dbBasics.lastCommitTime = System.nanoTime();
            new dbSchedule();
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda zatwierdzajca wstawienie do bazy.
     */
    private static void dbCommit() {
        try {
            c.commit();
            untilCommitCount = 1;
            lastCommitTime = System.nanoTime();
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda stworzona na potrzeby wątku backup'u
     * do użytku tylko przez klase dbSchedule
     */
    public static void dbTaskCommit() {
        dbCommit();
    }

    /**
     * Metoda sprawdzająca ostanie wykonanie wstawiania do bazy, jeżeli jest
     * większe niż 2s albo było wykonanych więcej niż 100 operacji na danych
     * wykonuje wstawianie.
     */
    private static void dbCommitStack() {
        //long difference = System.nanoTime() - lastCommitTime;
        System.out.println(untilCommitCount + " " + lastCommitTime + " " + (System.nanoTime() > lastCommitTime + 300000));
        if (System.nanoTime() > lastCommitTime + 2000000000) {
            dbCommit();
        } else if (untilCommitCount < 100) {
            untilCommitCount++;
            lastCommitTime = System.nanoTime();
        } else {
            dbCommit();
        }
    }

    /**
     * pusto, bo w sumie to ma tylko trzymac połączenie z konstruktora
     *
     */
    @Override
    public void run() {

    }

    /**
     * Uniwersalna metoda wstawiania wartości do bazy
     *
     * @param id
     * @param column
     * @param value
     */
    private static void dbSetValue(int id, String column, int value) {
        try {
            String query = "UPDATE modules SET " + column + " = " + value + " WHERE id = " + id + ";";
            stmt.executeUpdate(query);

            //System.out.println("Update " + column.toUpperCase() + " value was successful.");
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Update " + column.toUpperCase() + " value was unsuccessful.");
        }
        dbCommitStack();
    }

    /**
     * Uniwersalana metoda zwracająca wartość typu int wybranej kolumny dla
     * konkretnego id
     *
     * @param id Typu int określa moduł
     * @param column Typu String określa kolumne
     */
    private static int dbGetValue(int id, String column) {
        String query = "SELECT " + column + " FROM modules WHERE id =" + id + ";";
        int value = 0;
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                value = rs.getInt(column);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error while getting value of " + column.toUpperCase() + ".");
        }
        return value;
    }

    /**
     * Metoda wykonująca zapytanie SQL tylko do wyświetlania danych. Ta metoda
     * ma na celu tylko wyświetlić dane z CAŁEJ tabeli. Do wyświetlania
     * pojedyńczych kolumn użyj innej metody.
     *
     * Wprowadzenie zapytania typu INSERT albo UPDATE nie będzie funkcjonować
     *
     * @param query wejściowe dowolne zapytanie użytkownika
     * @throws SQLException
     */
    public static void dbSelect(String query) throws SQLException {
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
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
            System.out.println(id + "\t" + name
                    + "\t" + curr_lvl
                    + "\t" + cost
                    + "\t" + points
                    + "\t" + available
                    + "\t" + active
                    + "\t" + owned
                    + "\t" + successful
                    + "\t" + unsuccessful
            );
        }

        rs.close();
        System.out.println("Operation successful");
    }

    /**
     * Metoda dodająca do bazy modułów wpis o minimalnych wymaganiach, tzn. id i
     * nazwe modułu
     *
     * @param id wejściowy parametr dla operacji wstawiania do bazy danych.
     * Klucz główny.
     * @param name wejściowy parametr dla operacji wstawiania do bazy danych.
     * Nazwa osiągnięcia
     * @throws SQLException
     */
    public static void dbInsert(int id, String name) throws SQLException {
        String query = "INSERT INTO modules (id,name) VALUES (" + id + ", '" + name + "');";
        stmt.executeUpdate(query);
        dbCommitStack();
        System.out.println("Insert successful.");
    }

    /**
     * Metoda dodająca do bazy modułów kompletny wpis
     *
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
    public static void dbInsert(int id, String name, int curr_lvl, int cost, int points,
            int available, int active, int owned, int successful, int unsuccessful) throws SQLException {
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
        dbCommitStack();
        System.out.println("Insert successful.");
    }

    /**
     * Metoda dodająca do bazy danych tylko znaczące pola przy tworzeniu modułu
     *
     * @param id
     * @param name
     * @param cost
     * @throws SQLException
     */
    public static void dbInsert(int id, String name, int cost) throws SQLException {
        String query = "INSERT INTO modules (id, name, cost) VALUES "
                + "(" + id + ", '" + name + "'"
                + ", " + cost
                + ");";
        stmt.executeUpdate(query);
        dbCommitStack();
        System.out.println("Insert successful.");
    }

    /**
     * Metoda aktualizująca cene modułu
     *
     * @param id
     * @param cost
     */
    public static void dbSetCost(int id, int cost) {
        dbSetValue(id, "cost", cost);
    }

    /**
     * Metoda aktualizująca status posiadania modułu
     *
     * @param id
     * @param owned
     */
    public static void dbSetOwned(int id, int owned) {
        dbSetValue(id, "owned", owned);
    }

    /**
     * Metoda ustalająca ilość poprawnie rozwiązanych zadań //private
     *
     * @param id
     * @param successful
     */
    private static void dbSetSuccessful(int id, int successful) {
        dbSetValue(id, "successful", successful);
    }

    /**
     * Metoda ustawiająca ilość błednie rozwiązanych zadań //private
     *
     * @param id
     * @param unsuccessful
     */
    private static void dbSetUnsuccessful(int id, int unsuccessful) {
        dbSetValue(id, "unsuccessful", unsuccessful);
    }

    /**
     * Metoda wstawiająca podaną wartość do kolumny active podanego id
     *
     * @param id
     * @param value
     */
    public static void dbSetActive(int id, int value) {
        dbSetValue(id, "active", value);
    }

    /**
     * Metoda wstawiająca podaną wartość do kolumny CURR_LVL dla określonego ID
     *
     * @param id
     * @param value
     */
    public static void dbSetCurr_lvl(int id, int value) {
        dbSetValue(id, "curr_lvl", value);
    }

    /**
     * Metoda wstawiająca podaną wartość do kolumny POINTS dla określonego ID
     *
     * @param id
     * @param value
     */
    public static void dbSetPoints(int id, int value) {
        dbSetValue(id, "points", value);
    }
    
    /**
     * Metoda zwracająca wartość kolumny CURR_LVL dla określonego ID
     *
     * @param id
     * @return
     */
    public static int dbGetCurr_lvl(int id) {
        return dbGetValue(id, "curr_lvl");
    }

    /**
     * Medota zwracająca wartość kolumny POINTS dla określonego ID
     *
     * @param id
     * @return
     */
    public static int dbGetPoints(int id) {
        return dbGetValue(id, "points");
    }

     /**
     * Metoda zwracająca cene modułu
     *
     * @param id
     * @return Cena modułu
     */
    public static int dbGetCost(int id) {
        return dbGetValue(id, "cost");
    }

    /**
     * Metoda zwracająca prawda fałsz z kolumny active dla konkretnego id
     *
     * @param id
     * @return
     */
    public static boolean dbGetActive(int id) {
        return dbGetValue(id, "active") == 1;
    }

    /**
     * Metoda zwracająca prawda fałsz w zależności od zawartości kolumny owned
     * dla konkretnego id
     *
     * @param id
     * @return
     */
    public static boolean dbGetOwned(int id) {
        int value = dbGetValue(id, "owned");
        return value == 1;
    }

    /**
     * Metoda zwracająca prawda fałsz w zależności od zawarości kolumny
     * available dla konkretnego id
     *
     * @param id
     * @return
     */
    public static boolean dbGetAvailable(int id) {
        int value = dbGetValue(id, "available");
        return value == 1;
    }
    
    /**
     * Metoda zdobywająca ilość poprawnie rozwiązanych zadań
     *
     * @param id
     * @return Zwraca wartość kolumny successful dla konkretnego id
     */
    public static int dbGetSuccessful(int id) {
        return dbGetValue(id, "successful");
    }
    
    /**
     * Metoda zdobywająca ilość błednie rozwiązanych zadań
     *
     * @param id
     * @return Zwraca wartość kolumny unsuccessful dla konkretnego id
     */
    public static int dbGetUnsuccessful(int id) {
        return dbGetValue(id, "unsuccessful");
    }
    
    public static ArrayList dbGetArrayListNames(){
        String query = "SELECT name FROM modules";
        ArrayList value = new ArrayList();
        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String val = rs.getString("name");
                value.add(val);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Error while getting value of " + column.toUpperCase() + ".");
        }
        return value;
    }

    public static ArrayList<Modul> dbGetArrayListModules() throws SQLException {
        ResultSet rs = stmt.executeQuery("select * from modules");
        ArrayList<Modul> modules = new ArrayList();
        while (rs.next()) {
            
            ArrayList<String> list= new ArrayList();
            list.add(Integer.toString(rs.getInt("id")));
            list.add(rs.getString("name"));
            list.add(Integer.toString(rs.getInt("curr_lvl")));
            list.add(Integer.toString(rs.getInt("cost")));
            list.add(Integer.toString(rs.getInt("points")));
            list.add(Integer.toString(rs.getInt("available")));
            list.add(Integer.toString(rs.getInt("active")));
            list.add(Integer.toString(rs.getInt("owned")));
            list.add(Integer.toString(rs.getInt("successful")));
            list.add(Integer.toString(rs.getInt("unsuccessful")));
            modules.add(new Modul(list));
        }

        rs.close();
        System.out.println("Operation successful");
        return modules;
    }
    /**
     * Metoda zwiększająca wartość w tabeli successful o 1
     *
     * @param id
     */
    public static void dbEarnedSuccessful(int id) {
        int successful = dbGetSuccessful(id);
        successful++;
        dbSetSuccessful(id, successful);
    }

    /**
     * Metoda zwiększająca wartość w tabeli unsuccessful o 1
     *
     * @param id
     */
    public static void dbEarnedUnsuccessful(int id) {
        int unsuccessful = dbGetUnsuccessful(id);
        unsuccessful++;
        dbSetUnsuccessful(id, unsuccessful);
    }

    /**
     * Metoda zapisująca stan bazy i zamykająca połączenie.
     */
    public static void dbEndConnection() {
        try {
            dbCommit();
            dbBasics.stmt.close();
            dbBasics.c.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbBasics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
