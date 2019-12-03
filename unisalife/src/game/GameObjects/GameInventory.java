/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import game.Interfaces.GameInventoryStrategy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * LinkedList -> ins in testa -> O(1)
 * By name -> O(n), trova posizione
 * By date -> O(1), aggiungi in testa
 *
 * 
 * 
 * 
 * @author cmarino
 */
public class GameInventory implements Iterable<Item>{

    private List<Item> inventory;
    private GameInventoryStrategy gis;
    
    public GameInventory(){
        this.inventory = new LinkedList<>();
        gis = new InventoryStrategyByTaken();
    }
    
    public GameInventory(LinkedList<Item> l){
        gis=new InventoryStrategyByTaken();
        this.inventory=l;
    }
    
    
    public int length(){
        return inventory.size();
    }
    
    
    public boolean addItem( Item i) {
        
        return gis.addItem(inventory,i);
        
    }

    
    public Item removeItem(int pos){
        
        Item ret = inventory.remove(pos);
        return ret;
        
    }
    
    public Item removeItem(String title) {
        
        for( Item x : inventory ){
            if(x.getTitle().equals(title) ){
                inventory.remove(x);
                return x;
            }
        }
        
        return null;
        
    }

    public List<Item> search(String s){
        //;
        return inventory.stream().filter( gi -> gi.getTitle().startsWith(s)).collect(Collectors.toList());
    }
    
    
    public void sortByTaken() {
        if(this.gis instanceof InventoryStrategyByName){
            this.gis = new InventoryStrategyByTaken();
            Collections.sort(this.inventory, this.gis);
        }
    }
    
    public void sortByName(){
        if(this.gis instanceof InventoryStrategyByTaken){
            this.gis = new InventoryStrategyByName();
            Collections.sort(inventory, this.gis);
        }
    }

    @Override
    public Iterator iterator() {
        return this.inventory.iterator();
    }

    
}
