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
    
    public void putItem(String item) {
        this.items.put(item, false);
    }
    
    public void setAvailability(String item, boolean bool) {
        this.items.replace(item, bool);
    }
    
    public boolean isAvailable() {
        boolean available = true;
        for (Boolean b : items.values()) {
            available = available && b;
        }
        return available;
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.subject);
        return hash;
    }
    
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
    
    public int getLevel() {
        return this.level;
    }
    
    public Subject getSubject() {
        return this.subject;
    }
    
    public Set<String> getItemList() {
        return this.items.keySet();
    }
    
    @Override
    public void send(Message mess) {        
    }
    
    @Override
    public void receive(Message mess) {
        setAvailability(mess.getId(), mess.getBool());
    }
    
    @Override
    public String getIndex() {
        return this.subject.getInfo();
    }
    
}
