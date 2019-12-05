/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisalife.inventory;

import java.util.List;

/**
 * Inventory implementation that allows the insertion of items in the inventory according to the lexicographical order
 * of their in-game name or title.
 * @author cmarino
 */
public class InventoryStrategyByName implements GameInventoryStrategy{

    

    
    @Override
    public int addItem(List<GameItem> l , GameItem i) {
        
        int ap = l.indexOf(i);
        
        if(ap!=-1)
            return -1;
        
        int p = 0;

        for( GameItem x : l){
            
            System.out.println("Comparing " + x + " vs  " + i );
            
            
            
            if( this.compare(i, x) < 0 ){          
                break;
            }
            
            p++;
            
        }
        
        i.setTaken();
        l.add(p, i);
        
        return p;
        
    }

    
    
    @Override
    public int compare(GameItem o1, GameItem o2) {
        
        if(o1 == null && o2 == null )
            return 0;
        if(o1 == null)
            return -1;
        if(o2 == null)
            return 1;
        
        return o1.getTitle().compareTo(o2.getTitle());
        
    }
    
    
    
}
