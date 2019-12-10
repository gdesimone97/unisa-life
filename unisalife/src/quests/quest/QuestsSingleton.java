/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import exam.booklet.Saveable;
import exam.booklet.Subject;
import exam.question.Materia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import quests.QuestsManagerSingleton;
import quests.mediator.Message;
import quests.mediator.User;
/**
 *
 * @author liovi
 */
public class QuestsSingleton  implements Saveable, Serializable{

    private EnumMap<Materia, Quest> quests;
    private ArrayList<Quest> availableQuests;
    private static QuestsSingleton instance = null;
    
    private QuestsSingleton(){
        this.quests = new EnumMap(Materia.class);
        for (Materia x : Materia.values()){
            quests.put(x, new Quest(x.toString()));
        }
    }
    
    public static QuestsSingleton getInstance(){
        if (instance == null)
            synchronized (QuestsManagerSingleton.class){
                if(instance == null)
                    instance = new QuestsSingleton();
            }
        return instance;
    }

    public EnumMap<Materia, Quest> getQuest() {
        return quests;
    }
    
    public List<Quest> getAvailableQuest(){
        return this.availableQuests;
    }
    
    @Override
    public Serializable save() {
        ArrayList<Serializable> list = new ArrayList<>();
        list.add(quests);
        list.add(availableQuests);
        return list;
    }

    @Override
    public void load(Serializable obj) {
        ArrayList<Serializable> list = (ArrayList<Serializable>) obj;
        this.quests = (EnumMap<Materia, Quest>) list.get(0);
        this.availableQuests = (ArrayList<Quest>) list.get(1);
    }

    
    
}
