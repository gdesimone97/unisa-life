/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests;

import exam.question.Materia;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import quests.mediator.*;
import quests.quest.Quest;
import quests.quest.QuestsSingleton;

/**
 * This class is used due to a necessity of manage the communication between
 * the booklet, the inventory and the quests
 * 
 * @author liovi
 */
public class QuestsManagerSingleton implements QuestMessages{
    
    private static QuestsManagerSingleton instance = null;
    private List<User> users;
    private HashMap<String,Materia> item; //elenco di item e domande con associate ad una materia
    private EnumMap<Materia, Quest> quests;
    
    private QuestsManagerSingleton(){
        this.users = new ArrayList<>();
        QuestsSingleton instanceQuests = QuestsSingleton.getInstance();
        quests = instanceQuests.getQuest();
    }
    
    /**
     *
     * @return an instance of QuestsManagerSingleton
     */
    public static QuestsManagerSingleton getInstance(){
        if (instance == null)
            synchronized (QuestsManagerSingleton.class){
                if(instance == null)
                    instance = new QuestsManagerSingleton();
            }
        return instance;
    }
    
    /**
     * This method is used to forward messages between the classes
     * 
     * @param mess contains the message to forward
     * @param user is the user who sended the message
     */
    @Override
    public void sendMessage(Message mess, User user) {
        Materia receiver = item.get(mess.getId());
        Quest q = quests.get(receiver);
        q.receive(mess);
    }

    /**
     * This method is used to register a new user to the list
     * 
     * @param user reference to the user
     */
    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
    
}
