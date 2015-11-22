/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym.modules;


import static java.lang.Math.pow;
import mathgym.Modul;

/**
 * aaaa
 *
 * @author Krzych
 */
public class Odejmowanie extends Modul {

    private static Odejmowanie instance;

    protected Odejmowanie() {
        setLvl(1);
        setMaxNumber(5);
        setPoints(1.5);
        setCost(300);
        setLvlUpCost(50);
    }

    public static Odejmowanie getInstance() {
        if (instance == null) {
            instance = new Odejmowanie();
            return instance;
        } else {
            return instance;
        }
    }

    public void lvlUp() {
        setLvl(getLvl() + 1);
        setLvlUpCost(getLvl()*15);
        setPoints(getPoints()+0.5);
        if (getLvl() == 2) {
            setMaxNumber(10);
        } else if (getMaxNumber() < 100) {
            setMaxNumber(getMaxNumber() + 10);
            setPoints(getPoints()+0.2);
        } else {
            setMaxNumber(getMaxNumber() * 10);
            setPoints(getPoints()+0.5);
        }

    }
    
    public void generate() {
        
    }
}

