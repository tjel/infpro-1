/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mathgym.X_Sklep.getInstance;
import mathgym.dbOps.dbBasics;
import mathgym.modules.Dodawanie;

/**
 *
 * @author Krzych
 */
public class MathGym2 {

    //tylko cos sprawdzam
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {

        Scanner in = new Scanner(System.in);
        int wybor;

        System.out.println("Witaj");

        dbBasics Baza = new dbBasics();
        try {
            for (Object object : Baza.dbGetArrayListModules()) {
                System.out.println(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MathGym2.class.getName()).log(Level.SEVERE, null, ex);
        }

        Player gracz = new Player(Baza);
        Player.setPoints(30);

        System.out.println(gracz);
        System.out.println("1 - zadanie\n2 - moduly\n3-sklep\n0-pomoc");

        while (true) {

            System.out.print("$ ");
            wybor = in.nextInt();
            if (wybor == 0) {
                System.out.println("1 - zadanie\n2 - moduly\n3-sklep\n0-pomoc");
            } else if (wybor == 1) {
                gracz.challenge();
            } else if (wybor == 2) {
                for (String s : X_Sklep.getKupione()) {
                    System.out.println(s);
                }
                System.out.println("Podaj nazwe modulu, ktory chcesz uruchomic");
                String w;
                in.nextLine();
                w = in.nextLine();
                Generator.setSkill(w);
            } else if (wybor == 3) {
                System.out.println("1 - kupic nowy\n2 - ulepszyc dostepny");
                wybor = in.nextInt();
                if (wybor == 1) {
                    System.out.println("dostepne moduly:");
                    System.out.println(X_Sklep.getDostepne());
                    String w;
                    in.nextLine();
                    System.out.print("$ ");
                    w = in.nextLine();
                    X_Sklep.buyItem(w);
                } else if (wybor == 2) {
                    System.out.println("kupione moduly: ");
                    System.out.println(X_Sklep.getKupione());
                    String w;
                    in.nextLine();
                    System.out.print("$ ");
                    w = in.nextLine();
                    X_Sklep.lvlUpItem(w);
                    System.out.println(gracz);
                }
            }

        }

    }
}
