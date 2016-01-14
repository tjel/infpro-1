/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym.dbOps;

import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author zajec_000
 */
public class dbSchedule{
    private static Timer dbTimer;
    private static final int seconds = 5;
    
    /**
     * Konstruktor tworzący obietk typu Timer następnie wywołujący klase TimerTask.
     */
    protected dbSchedule(){
        dbSchedule.dbTimer = new Timer();
        dbTimer.scheduleAtFixedRate(new dbTaskEvent(),10, seconds * 1000);
    }
    
    /**
     * metoda wykonujaca commit na bazie danych.
     */
    private static void dbSaveEvent(){
        dbBasics.dbTaskCommit();
        System.out.println("Database save operation successful.");
    }
    
    /**
     * klasa dbTaskEvent wykonująca co określony czas w konstruktorze metode dbSaveEvent
     */
    class dbTaskEvent extends TimerTask {
    @Override
    public void run() {
        dbSaveEvent();
    }
  }
}
