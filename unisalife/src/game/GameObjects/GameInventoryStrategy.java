/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.util.Comparator;
import java.util.List;

/**
 * 
 * Strategy interface that allows to implement different behaviors to insert and to manage
 * the inventory, it extends comparator in order to define different ways to manage the inventory
 * according to the comparator cryterion.
 * 
 * @author cmarino
 */
public interface GameInventoryStrategy extends Comparator<Item>{
    
    
    /**
     * 
     * @param l The inventory list where the item needs to be inserted.
     * @param i The item that has to be inserter
     * @return The position where the item is inserted in case of a success, -1 otherwise.
     */
    public int addItem( List<Item> l, Item i );
    
}
