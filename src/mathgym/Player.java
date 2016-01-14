/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym;

import mathgym.modules.Dodawanie;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mathgym.dbOps.dbBasics;

/**
 * aaaa
 *
 * @author Krzych
 */
public class Player {
    
            Scanner in = new Scanner(System.in);
        int wybor;

    

    List<Modul> modules;
    private Modul activeModule;

    public Player(dbBasics _baza) {
        


        modules = _baza.dbGetArrayListNames();
        modules.add(Dodawanie.getInstance());
        activeModule = Dodawanie.getInstance();
    }
    
    public void checkDB() {
        
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
        if ( activeModule.genExercise() == 1) {
            setPoints(getPoints()+activeModule.getPoints());
            
        }
        
        
        
    }

}
