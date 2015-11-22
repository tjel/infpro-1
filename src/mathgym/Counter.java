/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgym;

/**
 * aaaa
 * @author Krzych
 */
public class Counter {
    private int i;
    
    public Counter() {
        i = 0;
    }
    
    public Counter(int i) {
        this.i = i;
    }
    
    public void addOne() {
        i++;
    }
    
    public void add(int i) {
        this.i += i;
    }
    
    public void minusOne() {
        i--;
    }
    
    public void minus(int i) {
        this. i -= i;
    }
}
