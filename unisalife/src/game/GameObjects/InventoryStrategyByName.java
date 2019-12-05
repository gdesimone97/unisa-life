/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import game.Interfaces.GameInventoryStrategy;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author cmarino
 */
public class InventoryStrategyByName implements GameInventoryStrategy, Comparator<Item>{

    

    
    @Override
    public boolean addItem(List<Item> l , Item i) {
        
        int ap = l.indexOf(i);
        
        if(ap!=-1)
            return false;
        
        int p = 0;

        for( Item x : l){
            
            if( this.compare(i, x) >= 0 ){          
                break;
            }
            
            p++;
            
        }
        
        l.add(p, i);
        
        return true;
        
    }

    @Override
    public int compare(Item o1, Item o2) {
        
        return o1.getTitle().compareTo(o2.getTitle());
        
    }
    
    
    
}
