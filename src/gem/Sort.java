/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gem;

import java.util.List;

/**
 *
 * @author margarita
 */
public abstract class Sort<T> {
    
    public abstract void sort(List<T> a);
    
    abstract boolean less(T a, T b);
    
    void exch(List<T> a, int i, int j) {
        T t = a.get(i);
        a.add(i, a.get(j));
        a.remove(i + 1);
        a.add(j, t);
        a.remove(j + 1);
    }
    
    boolean isSorted(List<T> a) {
        for (int i = 1; i < a.size(); ++i) {
            if (less(a.get(i), (T)a.get(i - 1))) {
                return false;
            }
        }
        return true;
    }
    
    public String show(List<T> a) {
        String res = "";
        for (T c: a) {
            res += c;
            res += "\n";
        }
        return res;
    }
}