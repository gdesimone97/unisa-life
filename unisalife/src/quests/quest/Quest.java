/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import java.io.Serializable;
import java.util.HashMap;
import quests.QuestsManagerSingleton;
import quests.mediator.Message;
import quests.mediator.User;

/**
 *
 * @author liovi
 */
public class Quest extends User implements  Serializable {

    private HashMap<String,Boolean> items;
    private boolean done;

    public Quest(String name) {
        super();
        super.name = name;
        super.mediator = QuestsManagerSingleton.getInstance();
        mediator.addUser(this);
        
        this.items = new HashMap<>();
        this.done = false;
    }   

    public void setItemsExam(String item) {
        this.items.put(item, false);
    }
    
    public void setAvailability(String item, boolean bool){
        this.items.replace(item, bool);
    }

    public boolean isAvailable() {
        boolean available = true;
        for(Boolean b : items.values())
            available = available & b;
        
        return available;
    }
    
    public void finish(){
        this.done = true;
    }
    
    public boolean isDone(){
        return this.done;
    }
    
    
    @Override
    public void send(Message mess) {}

    @Override
    public void receive(Message mess) {
        setAvailability(mess.getId(),mess.getBool());
    }

 }

