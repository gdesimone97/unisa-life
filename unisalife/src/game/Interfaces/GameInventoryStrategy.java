/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Interfaces;

import game.GameObjects.Item;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author cmarino
 */
public interface GameInventoryStrategy extends Comparator<Item>{
    
    
    public boolean addItem( List<Item> l, Item i );
    
}
