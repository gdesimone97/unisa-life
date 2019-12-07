/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests;

import exam.booklet.BookletSingleton;
import java.util.ArrayList;
import java.util.List;
import quests.mediator.*;

/**
 *
 * @author liovi
 */
public class QuestsManagerSingleton implements QuestMessages{
    
    private static QuestsManagerSingleton instance = null;
    private List<User> users = new ArrayList<>();
    BookletSingleton bookletInstance = BookletSingleton.getInstance();
//    GameInventory gameInventoryInstance = GameInventory.getInstance();
    
    private QuestsManagerSingleton(){
        
    }
    
    public static QuestsManagerSingleton getInstance(){
        if (instance == null)
            synchronized (QuestsManagerSingleton.class){
                if(instance == null)
                    instance = new QuestsManagerSingleton();
            }
        return instance;
    }
    
    @Override
    public void sendMessage(Message mess, User user) {
        for(User u : this.users){
            if( u != user){
                u.receive(mess);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
    
}
