/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests;

import game.Interfaces.Initializable;
import java.util.ArrayList;
import quests.mediator.*;
import quests.quest.Quests;

/**
 * This class is used due to a necessity of manage the communication between the
 * booklet, the inventory and the quests
 *
 * @author liovi
 */
public class QuestsManager implements QuestMessages, Initializable {

    private static QuestsManager instance = null;
    private ArrayList<User> users;

    private QuestsManager(){
    }

    /**
     * 
     * @return an instance of QuestsManager
     */
    public static QuestsManager getInstance() {
        if (instance == null)
            synchronized (QuestsManager.class) {
            if (instance == null) {
                instance = new QuestsManager();
            }
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
        Quests.getInstance().receive(mess);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws InitException {
        this.users = new ArrayList<>(); 
    }

}
