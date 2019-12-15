/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import exam.booklet.Saveable;
import java.io.Serializable;
import java.util.HashMap;
import quests.mediator.Message;
import quests.mediator.User;

/**
 *
 * @author liovi
 */
public class QuestsSingleton  extends User implements Saveable, Serializable{

    private HashMap<String,Quest> quests;
    private int currentLevel;
    private static QuestsSingleton instance = null;
    
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

    private void loadNewQuests(){
        
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
            this.loadNewQuests();
        }
        
    }

    
    
}
