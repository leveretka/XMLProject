/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gem;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author margarita
 */
public class GemsContainer implements Comparator<Gem>, Iterable<Gem>{

    private List<Gem> container;

    public GemsContainer(List<Gem> container) {
        this.container = container;
    }
    
    public void sort() {
        new Quick<Gem>(){
            
            @Override
            boolean less(Gem o1, Gem o2) {
                return compare(o1, o2) < 0;
            }
        }.sort(container);
    }
    
    @Override
    public int compare(Gem o1, Gem o2) {
        return (int) (o1.getValue() - o2.getValue());
    }

    @Override
    public Iterator<Gem> iterator() {
        return container.iterator();
    }
    
}
