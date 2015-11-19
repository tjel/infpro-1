/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym2;

/**
 * aaaa
 *
 * @author Krzych
 */
public class Modul {

    private static int lvl;
    private static int maxNumber;
    private static double points;
    private static double cost;
    private static double lvlUpCost;
    private static boolean available;
    private int answer;
    

    
    
    /**
     * @return the lvl
     */
    public static int getLvl() {
        return lvl;
    }

    /**
     * @param aLvl the lvl to set
     */
    public static void setLvl(int aLvl) {
        lvl = aLvl;
    }

    /**
     * @return the maxNumber
     */
    public static int getMaxNumber() {
        return maxNumber;
    }

    /**
     * @param aMaxNumber the maxNumber to set
     */
    public static void setMaxNumber(int aMaxNumber) {
        maxNumber = aMaxNumber;
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
    
    public void lvlUp(){
        System.out.println("LEVEL UP NOT SET");
    }

    public String getExercise() {
        generate();
        return "generate exercise not set";
    }



    /**
     * @param aLvlUpCost the lvlUpCost to set
     */
    public static void setLvlUpCost(double aLvlUpCost) {
        lvlUpCost = aLvlUpCost;
    }

    /**
     * @return the lvlUpCost
     */
    public static double getLvlUpCost() {
        return lvlUpCost;
    }

    /**
     * @return the cost
     */
    public static double getCost() {
        return cost;
    }

    /**
     * @param aCost the cost to set
     */
    public static void setCost(double aCost) {
        cost = aCost;
    }

    /**
     * @return the available
     */
    public static boolean isAvailable() {
        return available;
    }

    /**
     * @param aAvailable the available to set
     */
    public static void setAvailable(boolean aAvailable) {
        available = aAvailable;
    }

    public void generate() {
        System.out.print("error, generate from parent class");
    }
  
}
