/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisalife.inventory;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class deserves as a model for the in-game inventory, so it has the 
 * responsability to hold the items that a player collected during the gameplay.
 * The inventory is thought to be able to show the items of a player ordered 
 * by their title or by the moment they were taken.
 */
public class GameInventory implements Iterable<GameItem>, Serializable{

    private List<GameItem> inventory;
    private GameInventoryStrategy gis;
    
    /**
     * The constructor just initialized the data structure that uses the inventory
     * and set the default strategy ( items are sorted by their taken time, according
     * to a LIFO policy)
     */
    public GameInventory(){
        this.inventory = new LinkedList<>();
        gis = new InventoryStrategyByTaken();
    }
    
    /**
     * 
     * @return the number of elements stored in the inventory 
     */
    public int length(){
        return inventory.size();
    }
    
    /**
     * 
     * @param i The item that has to be inserter
     * @return The position in the data structure where i has been inserted, -1 if the element was already inserter.
     */
    public int addItem( GameItem i) {
        
        return gis.addItem(inventory,i);
        
    }

    /**
     * 
     * @param pos The position where it's located the element I want to remove
     * @return The removed element
     */
    public GameItem removeItem(int pos){
        
        GameItem ret = inventory.remove(pos);
        return ret;
        
    }
    
    /**
     * 
     * @param title The title of the element the users want to remove.
     * @return The removed element
     */
    public GameItem removeItem(String title) {
        
        for( GameItem x : inventory ){
            if(x.getTitle().equals(title) ){
                inventory.remove(x);
                return x;
            }
        }
        
        return null;
        
    }

    /**
     * 
     * @param s The title or part of it of the object the users is looking for.
     * @return A list of the objects which title starts with the string s.
     */
    public List<GameItem> search(String s){
        //;
        return inventory.stream().filter( gi -> gi.getTitle().toLowerCase().startsWith(s)).collect(Collectors.toList());
    }
    
    /**
     * Set the strategy, the list will be ordered by taken parameter and so the add
     * mechanism will change underneath.
     */
    public void sortByTaken() {
        if(this.gis instanceof InventoryStrategyByName){
            this.gis = new InventoryStrategyByTaken();
            Collections.sort(this.inventory, this.gis);
        }
    }
    
    /**
     * Set the strategy, the list will be ordered by the name of the items
     * and so the add mechanism will change underneath.
     */
    public void sortByName(){
        if(this.gis instanceof InventoryStrategyByTaken){
            this.gis = new InventoryStrategyByName();
            Collections.sort(inventory, this.gis);
        }
    }

    /**
     * 
     * @return An iterator over the original data structure. 
     */
    @Override
    public Iterator iterator() {
        return this.inventory.iterator();
    }

    
}
