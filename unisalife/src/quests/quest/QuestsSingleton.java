/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import exam.booklet.Saveable;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author liovi
 */
public class QuestsSingleton  implements Saveable, Serializable{

    private HashMap<String,Quest> quests;
    private static QuestsSingleton instance = null;
    
    private QuestsSingleton(){
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

    
    
}
