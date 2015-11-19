/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgym2;

import java.util.ArrayList;
import java.util.List;
import mathgym2.modules.*;

/**
 * aaaa
 *
 * @author Krzych
 */
public class X_Sklep {

    private static List<String> dostepne = new ArrayList();
    private static List<String> kupione = new ArrayList();

    private static int index;

    private static X_Sklep instance;

    protected X_Sklep() {
        kupione.add("Dodawanie");
        Dodawanie.getInstance();
    }

    public static X_Sklep getInstance() {
        if (instance == null) {
            instance = new X_Sklep();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * @return the dostepne
     */
    public static List<String> getDostepne() {
        return dostepne;
    }

    /**
     * @return the kupione
     */
    public static List<String> getKupione() {
        return kupione;
    }

    // sprawdza czy mozna kupic modul o podanej nazwie, ustawia zmienna index na
    // index elementu w liscie 'dostepne', zeby go z tej listy potem usunac
    private static boolean moznaKupic(String name) {
        boolean b = false;
        for (int i = 0; i < dostepne.size(); i++) {
            if (dostepne.get(i).equals(name)) {
                b = true;
                index = i;
            }
        }
        return b;
    }

    public static void buyItem(String name) {

        if (moznaKupic(name)) {
            kupione.add(name);
            dostepne.remove(index);

            if (name.equals("Odejmowanie")) {
                Odejmowanie.getInstance();
            }

        } else {
            System.out.println("error: zla nazwa skilla");
        }

    }

    public static void setAvailable(String name) {
        dostepne.add(name);
    }

    public static boolean modulDostepny(String name) {
        for (String s : dostepne) {
            if (s.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean modulKupiony(String name) {
        for (String s : kupione) {
            if (s.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void lvlUpItem(String name) {
        if (name.equals("Dodawanie")) {
            if (Player.getPoints() >= Dodawanie.getLvlUpCost()) {
                Player.setPoints(Player.getPoints() - Dodawanie.getLvlUpCost());
                Dodawanie.lvlUp();
            }
        }
        if (name.equals("Odejmowanie")) {
            if (Player.getPoints() >= Odejmowanie.getLvlUpCost()) {
                Player.setPoints((Player.getPoints() - Odejmowanie.getLvlUpCost()));
                Odejmowanie.lvlUp();
            }
        }
        if (name.equals("DodawanieWielokrotne")) {
            DodawanieWielokrotne.lvlUp();
        }

    }
}
