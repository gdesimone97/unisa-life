/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import game.Interfaces.Initializable;
import saving.Saveable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import quests.QuestsManager;
import quests.mediator.Message;
import quests.mediator.User;

/**
 *
 * @author liovi
 */
public class Quests extends User implements Saveable, Serializable, Initializable {

    private HashMap<String, Quest> quests;
    private static Quests instance = null;

    private Quests() {
        super();
    }

    public static Quests getInstance() {
        if (instance == null)
            synchronized (Quests.class) {
            if (instance == null) {
                instance = new Quests();
            }
        }
        return instance;
    }

    public Quest getQuest(String s) {
        return this.quests.get(s);
    }

    public void putQuest(Quest q) {
        this.quests.put(q.getSubject().getInfo(), q);
    }

    @Override
    public Serializable save() {
        return null;
    }

    @Override
    public void load(Serializable obj) {

    }

    @Override
    public void send(Message mess) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadNewQuests(List<Quest> q) {

        this.quests = new HashMap<>();
        for (Quest quest : q) {
            this.quests.put(quest.getSubject().toString(), quest);
        }
    }

    @Override
    public void receive(Message mess) {
        //Messaggio = stringa +  bool -> se bool è true --> ho fatto quest'esame
        String quest = mess.getId();
        if (mess.getBool()) {

            quests.remove(quest);

        }

        if (quests.isEmpty()) {
            QuestsManager.getInstance().levelEnded();
            // chiamata a livello superiore per informare che le quest correnti sono terminate
        }

    }

    @Override
    public void init() {
        this.name = "Quests";
        this.quests = new HashMap<>();
    }

    public HashMap<String, Quest> getQuests() {
        return quests;
    }

    
    

}
