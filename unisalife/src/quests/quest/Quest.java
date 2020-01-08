/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import database.Storable;
import exam.booklet.Subject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import org.dizitart.no2.IndexType;
import quests.QuestsManager;
import quests.mediator.Message;
import quests.mediator.User;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

/**
 *
 * @author liovi
 */
@Indices({
    @Index(value = "level", type = IndexType.NonUnique)
})

public class Quest extends User implements Serializable, Storable {
    
    private int level;
    private HashMap<String, Boolean> items;
    private Subject subject;
    private boolean done;
    
    private Quest() {
        super.mediator = QuestsManager.getInstance();
        mediator.addUser(this);      
    }
    
    /**
     * Constructor used to initialize the quest
     * 
     * @param level an int that indicates the level of the quest
     * @param subject a Subject relative to the Quest
     * 
     * @throws QuestNotValidException
     */
    public Quest(int level, Subject subject) throws QuestNotValidException {
        super();
        super.name = subject.getInfo();
        super.mediator = QuestsManager.getInstance();
        mediator.addUser(this);
        
        this.subject = subject;
        this.items = new HashMap<>();
        this.done = false;
        this.level = level;
        if (level < 0) {
            throw new QuestNotValidException("The level you entered is not a positive integer");
        }
    }
    
    /**
     * Used to initialize the required items
     * 
     * @param item a String that represents the item's name
     */
    public void putItem(String item) {
        this.items.put(item, false);
    }
    
    /**
     * This method is used to set an item as catched
     * 
     * @param item a String that represents the item's name
     * @param bool a boolean that indicates the state of the item
     */
    public void setAvailability(String item, boolean bool) {
        this.items.replace(item, bool);
    }
    
    /**
     * This method is used to check if the quest is available or not
     * (Checks if all the requirements are satisfied)
     * 
     * @return a boolean that represents if all the requirements are or not are satisfied
     */
    public boolean isAvailable() {
        boolean available = true;
        for (Boolean b : items.values()) {
            available = available && b;
        }
        return available;
    }
    
    /**
     * This method is used to check if the quest is done
     * 
     * @return a boolean that indicates if the quest is already done or not
     */
    public boolean isDone() {
        return this.done;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.subject);
        return hash;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quest other = (Quest) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        return true;
    }
    
    /**
     * This method is used to check the level of the quest
     * 
     * @return an int that indicates the level of the quest
     */
    public int getLevel() {
        return this.level;
    }
    
    /**
     * This method is used to view the Subject of the quest
     * 
     * @return a Subject that is the subject of the quest
     */
    public Subject getSubject() {
        return this.subject;
    }
    
    /**
     * This method is used to view the required items of the quest
     * 
     * @return the Set of items of the quest
     */
    public Set<String> getItemList() {
        return this.items.keySet();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void send(Message mess) {        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void receive(Message mess) {
        setAvailability(mess.getId(), mess.getBool());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndex() {
        return this.subject.getInfo();
    }
    
}
