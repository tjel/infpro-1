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
        
        for(Object obj : _baza.dbGetArrayListModules()) {
            modules.add(obj);
        }
        
        for(Modul m : modules) {
            if(m.getActive() == 1) activeModule = m;
        }
    }
    
    public void activateModule(String _name) {
        for(Modul m : modules) {
            if (m.getActive() == 1) m.setActive(0);
            if (m.getName().equals(_name)) m.setActive(1);
        }
    }

}
