/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym;

import mathgym.modules.Dodawanie;
import java.util.ArrayList;
import java.util.List;

/**
 * aaaa
 *
 * @author Krzych
 */
public class Player {

    private String name;
    private int lvl;
    private static double points;
    List<Modul> modules;
    private Modul activeModule;

    public Player(String name) {
        this.name = name;
        lvl = 1;
        points = 0;
        modules = new ArrayList();
        modules.add(Dodawanie.getInstance());
        activeModule = Dodawanie.getInstance();
    }

    /**
     * @return the lvl
     */
    public int getLvl() {
        return lvl;
    }

    /**
     * @param lvl the lvl to set
     */
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    /**
     * @return the points
     */
    public static double getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public static void setPoints(double points) {
        Player.points = points;
    }

    @Override
    public String toString() {
        return name + " lvl: " + lvl + " pkt: " + points;
    }

    /**
     * @return the activeModule
     */
    public Modul getActiveModule() {
        return activeModule;
    }

    /**
     * @param activeModule the activeModule to set
     */
    public void setActiveModule(Modul activeModule) {
        this.activeModule = activeModule;
    }
    
    public void challenge() {
        System.out.println(activeModule.getExercise());
        
        /**
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         *       TU RÓB
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
        */
    }

}
