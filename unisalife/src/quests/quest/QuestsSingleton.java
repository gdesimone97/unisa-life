/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import exam.booklet.Saveable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import quests.mediator.Message;
import quests.mediator.User;

/**
 *
 * @author liovi
 */
public class QuestsSingleton  extends User implements Saveable, Serializable{

    private HashMap<String,Quest> quests;
    private static QuestsSingleton instance = null;
    private int currentLevel = 0;
    
    private QuestsSingleton(){
        super();
        this.name = "QuestsSingleton";
        this.quests = new HashMap<>();
    }
    
    public static QuestsSingleton getInstance(){
        if (instance == null)
            synchronized (QuestsSingleton.class){
                if(instance == null)
                    instance = new QuestsSingleton();
            }
        return instance;
    }

    public Quest getQuest( String s) {
        return this.quests.get(s);
    }
    
    public void putQuest(Quest q){
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

    public void loadNewQuests(List<Quest> q){
        // prima volta:
        /*
        list<quest>, list<item> = dbms.getQuestsFromLevel(0);
        QuestsSingleton.getInstance().loadNewQeuests(list);
        */

        // lista fornita dal game manager, che ha già effettuato l'accesso al database
        /*  {esempio di comportamento del game manager}
        ....-> dopo che è stata fatta la finish
        DatabaseManagaer dbms = new...
        list<quest>, list<item> = dbms.getQuestsFromLevel(QuestsSingleton.getInstance().getCurrentLevele());
        QuestsSingleton.getInstance().loadNewQeuests(list);
        ..ora metto nella mappa tutti gli oggetti presenti nella lista
        */
        this.quests = new HashMap<>();
        for(Quest quest: q){
            this.quests.put(quest.getSubject().toString(), quest);
        }
    }
    
    @Override
    public void receive(Message mess) {
        //Messaggio = stringa +  bool -> se bool è true --> ho fatto quest'esame
        String quest = mess.getId();
        if(mess.getBool()){
            
            quests.remove(quest);
            
        }
        
        if(quests.isEmpty()){
            this.currentLevel++;
            // chiamata a livello superiore per informare che le quest correnti sono terminate
        }
        
    }

    
    
}
