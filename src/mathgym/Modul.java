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
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class Modul {

    private int id;
    private String name;
    private int curr_lvl;
    private int cost;
    private int points;
    private int available;
    private int active;
    private int owned;
    private int successful;
    private int unsuccessful;
    
    public Modul(ArrayList<String> list) {
        id = Integer.parseInt(list.get(0));
        name = list.get(1);
        curr_lvl = Integer.parseInt(list.get(2));
        cost = Integer.parseInt(list.get(3));
        points = Integer.parseInt(list.get(4));
        available = Integer.parseInt(list.get(5));
        active = Integer.parseInt(list.get(6));
        owned = Integer.parseInt(list.get(7));
        successful = Integer.parseInt(list.get(8));
        unsuccessful = Integer.parseInt(list.get(9));
                
}

    public void lvlUp() {
        System.out.println("LEVEL UP NOT SET");
    }
    
}
