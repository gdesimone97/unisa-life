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
import quests.QuestsManagerSingleton;
import quests.mediator.Message;
import quests.mediator.User;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;
import saving.Saveable;

/**
 *
 * @author liovi
 */
@Indices({
    @Index(value = "level", type = IndexType.NonUnique)
})
public class Quest extends User implements Serializable, Storable, Saveable {

    private int level;
    private HashMap<String, Boolean> items;
    private Subject subject;
    private boolean done;

    private Quest() {

    }

    public Quest(int level, Subject subject) throws QuestNotValidException {
        super();
        this.level = level;
        super.name = subject.getInfo();
        super.mediator = QuestsManagerSingleton.getInstance();
        mediator.addUser(this);

        if (level < 0) {
            throw new QuestNotValidException("The level you entered is not a positive integer");
        }

        this.subject = subject;
        this.items = new HashMap<>();
        this.done = false;
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

    public void finish() {
        this.done = true;
        this.send(new Message(this.name, true));

    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean bool) {
        this.done = bool;
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

    public int getLeve() {
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

        this.mediator.sendMessage(mess, this);

    }

    @Override
    public void receive(Message mess) {
        setAvailability(mess.getId(), mess.getBool());
    }

    @Override
    public Serializable save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(Serializable obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIndex() {
        return this.subject.getInfo();
    }

}
