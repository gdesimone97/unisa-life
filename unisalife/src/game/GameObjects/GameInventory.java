/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Initializable;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import quests.QuestsManager;
import quests.mediator.Message;
import quests.mediator.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import quests.ItemDef;
import saving.Saveable;
import saving.exceptions.LoadingException;
import unisagui.GuiManager;

/**
 *
 * @author cmarino
 */
public class GameInventory extends User implements Iterable<Item>, Saveable, Initializable{
    
    private HashMap< String, Item > items;
    private ArrayList<Item> view;
    private Comparator<Item> comp;
    private static GameInventory instance = null;

    private GameInventory(){
        
        super();   
        
    }
    
    /**
     *
     * @return The instance of the singleton object 
     */
    public static GameInventory getInstance(){
        if (instance == null)
            synchronized (GameInventory.class){
                if(instance == null)
                    instance = new GameInventory();
            }
        return instance;
    }
    
    /**
     * This method notifies the Quest Manager that an item is  added and so it's available to be used for related quests. 
     * @param mess The message to send to QuestManager
     */
    @Override
    public void send(Message mess) {
        mediator.sendMessage(mess, this);
    }

    /**
     * Since the inventory is not updated externally, this methods doesn't need to be implemented.
     * @param mess The message to send to QuestManager
     */
    @Override
    public void receive(Message mess) {}

    /**
     *
     * @return The number of items contained in the inventory
     */
    public int length(){
        return items.size();
    }
    
    /**
     * 
     * @param i The item that has to be inserted
     * @return The position in the data structure where i has been inserted, -1 if the element was already inserter.
     */
    public int addItem(Item i) {
        
        Item c = this.items.putIfAbsent(i.getInfo() , i);
        if( c != null )
            throw new RuntimeException("The item you're trying to add is already in the inventory");
        i.setTaken();
        Message msg = new Message(i.getInfo() , true ); //prepare the message with the added object
        send(msg); //then sends it
        int pos = Collections.binarySearch(view, i, comp);
        //int pos = view.indexOf(i);
        view.add(-(pos+1), i);
        GuiManager.getInstance().updateInventoryDialog();
        return pos;
        
    }

    /**
     * 
     * @param pos The position where it's located the element I want to remove
     * @return The removed element
     */
    public Item removeItem(int pos){
     
        Item i = view.remove(pos);
        GuiManager.getInstance().updateInventoryDialog();
        return removeItem(i);
        
    }
    
    public Item removeItem(Item i ){

        Item r = items.remove(i.getInfo());
        if( r == null )
            throw new RuntimeException("Item is not in the inventory, impossible to remove");
        GuiManager.getInstance().updateInventoryDialog();
        return i;
    }
    
    

    /**
     * 
     * @param s The title or part of it of the object the users is looking for.
     * @return A list of the objects which title starts with the string s.
     */
    public List<Item> search(String s){
        //;
        return ((items.values().stream())).filter( gi -> gi.getInfo().toLowerCase().startsWith(s)).collect(Collectors.toList());
    }
    
    /**
     * Set the strategy, the list will be ordered by taken parameter and so the add
     * mechanism will change underneath.
     */
    public void sortByTaken() {
        if(! (this.comp instanceof TakenComparator) ){
            this.comp = new TakenComparator();
            Collections.sort(view, comp);
        }
        
    }
    
    /**
     * Set the strategy, the list will be ordered by the name of the items
     * and so the add mechanism will change underneath.
     */
    public void sortByTitle(){
        if(! (this.comp instanceof TitleComparator) ){
            this.comp = new TitleComparator();
            Collections.sort(view, comp);
        }
    }

    /**
     * 
     * @return An iterator over the original data structure. 
     */
    @Override
    public Iterator iterator() {
        return this.view.iterator();
    }

    /**
     *
     * @return The object that need to be serialized
     */
    @Override
    public Serializable save() {
        return this.items; //cast to Serializable useful because ArrayList seems to not be Serializable
    }

    /**
     *
     * @param obj Load the state of the inventory by the serialized object "obj"
     */
    @Override
    public void load(Serializable obj)throws LoadingException {
        this.items = (HashMap<String,Item>) obj;
    }

    @Override
    public void init() throws InitException {
        super.name = "inventory";
        super.mediator = QuestsManager.getInstance();
        mediator.addUser(this);
        
        this.view = new ArrayList<>();
        this.items = new HashMap<>();
        this.comp = new TakenComparator();
    }

    private static class TakenComparator implements Comparator<Item> {

        public TakenComparator() {
        }


        @Override
        public int compare(Item o1, Item o2) {
            if(o1 == null )
                return o1==o2?0:-1;
            if(o2 == null )
                return 1;
            return o1.getTaken().compareTo(o2.getTaken());
        }
    }

    private static class TitleComparator implements Comparator<Item> {

        public TitleComparator() {
        }

        @Override
        public int compare(Item o1, Item o2) {
            if(o1 == null )
                return o1==o2?0:-1;
            if(o2 == null )
                return 1;
            return o1.getInfo().compareTo(o2.getInfo());
        }
    }
    
    
    
    
}
