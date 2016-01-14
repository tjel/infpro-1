/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym;

/**
 * aaaa
 *
 * @author Krzych
 */
import lombok.Getter;
import lombok.Setter;

public class Modul {

    @Setter @Getter private static int lvl;
    @Setter @Getter private static int maxNumber;
    @Setter @Getter private static double points;
    @Setter @Getter private static double cost;
    @Setter @Getter private static double lvlUpCost;
    @Setter @Getter private static boolean available;
    private int answer;

    public void lvlUp() {
        System.out.println("LEVEL UP NOT SET");
    }

    public void generate() {
        System.out.print("error, generate from parent class");
    }
    
    
    public int genExercise() {
        return -1;
    }


}
