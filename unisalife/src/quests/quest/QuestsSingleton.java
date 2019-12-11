/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import exam.booklet.Saveable;
import exam.question.Materia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import quests.ItemDef;
/**
 *
 * @author liovi
 */
public class QuestsSingleton  implements Saveable, Serializable{

    private EnumMap<Materia, Quest> quests;
    private static QuestsSingleton instance = null;
    
    private QuestsSingleton(){
        this.quests = new EnumMap(Materia.class);
        for (Materia x : Materia.values()){
            this.quests.put(x, new Quest(x.toString()));
        }
//        this.quests.get(Materia.matematica).setItemsExam(ItemDef.appuntidimatematica1.toString());
//        this.quests.get(Materia.matematica).setItemsExam(ItemDef.appuntidimatematica2.toString());
        this.quests.get(Materia.matematica).setItemsExam(ItemDef.calcolatrice.toString());
    }
    
    public static QuestsSingleton getInstance(){
        if (instance == null)
            synchronized (QuestsSingleton.class){
                if(instance == null)
                    instance = new QuestsSingleton();
            }
        return instance;
    }

    public EnumMap<Materia, Quest> getQuest() {
        return this.quests;
    }
    
    @Override
    public Serializable save() {
        ArrayList<Serializable> list = new ArrayList<>();
        list.add(quests);
        return list;
    }

    @Override
    public void load(Serializable obj) {
        ArrayList<Serializable> list = (ArrayList<Serializable>) obj;
        this.quests = (EnumMap<Materia, Quest>) list.get(0);
    }

    
    
}
