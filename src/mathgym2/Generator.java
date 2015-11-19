/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgym2;

import mathgym2.modules.Odejmowanie;
import mathgym2.modules.Dodawanie;
import java.util.*;

/**
 * aaaa
 * @author Krzych
 */
public class Generator {
    private static String activeSkill;
    private static final Random gen = new Random();
    
    private static int a, b;
    private static int answer;
    private static double points;
    private static String exString;
    
    
    private static Generator instance;

    /**
     * @return the answer
     */
    public static int getAnswer() {
        return answer;
    }

    /**
     * @param aAnswer the answer to set
     */
    public static void setAnswer(int aAnswer) {
        answer = aAnswer;
    }

    /**
     * @return the points
     */
    public static double getPoints() {
        return points;
    }

    /**
     * @param aPoints the points to set
     */
    public static void setPoints(double aPoints) {
        points = aPoints;
    }
    protected Generator() {}
    public static Generator getInstance() {
        if (instance == null) {
            instance = new Generator();
            return instance;
        }
        else return instance;
    }
    
    private static int genMax(int max) {
        int n = gen.nextInt();
        if (n < 0) n = -n;
        return n%(max);
    }
    
    public static String generateExercise() {
        if (activeSkill.equals("Dodawanie")) {
            a = genMax(Dodawanie.getMaxNumber()+1);
            b = genMax(Dodawanie.getMaxNumber()+1);
                 
            setAnswer(a + b);
            setPoints(Dodawanie.getPoints());
            return a + " + " + b;
        }
        else if(activeSkill.equals("Odejmowanie")) {
            a = genMax(Odejmowanie.getMaxNumber()+1);
            b = genMax(a);
            
            setAnswer(a - b);
            return a + " - " + b;
        }
        return "generateExercise error";
    }
    
    public static void setSkill(String name) {
        if(X_Sklep.modulKupiony(name)) activeSkill = name;
        else System.out.println("modul niedostepny");
        
    }
}
