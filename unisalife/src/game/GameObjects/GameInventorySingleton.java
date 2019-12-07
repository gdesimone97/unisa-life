/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import quests.QuestsManagerSingleton;
import quests.mediator.Message;
import quests.mediator.User;
import exam.booklet.Saveable;
import java.io.Serializable;
/**
 *
 * @author cmarino
 */
public class GameInventorySingleton implements User, Iterable<Item>, Saveable{
    
    private List<Item> inventory;
    private GameInventoryStrategy gis;
    private static GameInventorySingleton instance = null;

    private GameInventorySingleton(){
        
        this.inventory = new LinkedList<>();
        this.gis = new InventoryStrategyByTaken();
        
        
    }
    
    public GameInventorySingleton getInstance(){
        if (instance == null)
            synchronized (GameInventorySingleton.class){
                if(instance == null)
                    instance = new GameInventorySingleton();
            }
        return instance;
    }
    
    
    @Override
    public void send(Message mess) {
        
        
        
    }

    @Override
    public void receive(Message mess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int length(){
        return inventory.size();
    }
    
    /**
     * 
     * @param i The item that has to be inserter
     * @return The position in the data structure where i has been inserted, -1 if the element was already inserter.
     */
    public int addItem(Item i) {
        
        int pos =  gis.addItem(inventory,i);
        QuestsManagerSingleton.getInstance().sendMessage(new Message(i.getInfo(), true ), this);
        return pos;
    }

    /**
     * 
     * @param pos The position where it's located the element I want to remove
     * @return The removed element
     */
    public Item removeItem(int pos){
        
        Item ret = inventory.remove(pos);
        QuestsManagerSingleton.getInstance().sendMessage(new Message(ret.getInfo(), false), this);
        return ret;
        
    }
    
    /**
     * 
     * @param title The title of the element the users want to remove.
     * @return The removed element
     */
    public Item removeItem(String title) {
        
        for( Item x : inventory ){
            if(x.getTitle().equals(title) ){
                inventory.remove(x);
                QuestsManagerSingleton.getInstance().sendMessage(new Message(x.getInfo(), false), this);
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
    public List<Item> search(String s){
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

    @Override
    public Serializable save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(Serializable obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
