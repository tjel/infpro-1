/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym.modules;

import java.util.Random;
import java.util.Scanner;
import mathgym.Modul;
import mathgym.dbOps.dbBasics;

/**
 * aaaa
 *
 * @author Krzych
 */
public class Dodawanie extends Modul {

    private final static String name = "Dodawanie";
    private Random gen = new Random();

    private static long a, b, answer;

    Scanner in = new Scanner(System.in);

    private static Dodawanie instance;

    protected Dodawanie() {

        setLvl(1);
        setMaxNumber(5);
        setPoints(1);
        setLvlUpCost(10);
    }

    public static Dodawanie getInstance() {
        if (instance == null) {
            instance = new Dodawanie();
            return instance;
        } else {
            return instance;
        }
    }
    // zwieksza trudnosc zadan, podnosi ilosc punktow

    @Override
    public void lvlUp() {
        setLvl(getLvl() + 1);
        setLvlUpCost(getLvl() * 10);
        setPoints(getPoints() + 0.3);
        if (getLvl() == 2) {
            setMaxNumber(10);
        } else if (getLvl() > 2 && getLvl() <= 6) {
            setMaxNumber(getMaxNumber() + 10);
            if (getLvl() == 3) {
                Odejmowanie.setAvailable(true);
            }
        } else if (getLvl() == 7) {
            setMaxNumber(100);
            setPoints(2);
        } else {
            setMaxNumber(getMaxNumber() * 10);
            setPoints(getPoints() + 0.3);
            if (getLvl() == 10) {
                DodawanieWielokrotne.setAvailable(true);
            }
        }
    }

    @Override
    public void generate() {
        a = gen.nextInt();
        b = gen.nextInt();

        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }

        a = a % (getMaxNumber() + 1);
        b = b % (getMaxNumber() + 1);

        answer = a + b;

    }
    /**
     *  generate exercise for player
     * @return  1 if win; 0 if lose
     */
    @Override
    public int genExercise() {
        int odp;
        generate();
        System.out.println( a + " + " + b);
        System.out.println("$");
        odp = in.nextInt();
        
        if (odp == answer) {
            System.out.println("win");
            return 1;
        }
        else {
            System.out.println("lose");
            return 0;
        }
        
    }

}
