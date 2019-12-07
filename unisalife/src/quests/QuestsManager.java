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
public class QuestsManager implements QuestMessages{
    
    private List<User> users = new ArrayList<>();
    BookletSingleton bookletInstance = BookletSingleton.getInstance();
//    GameInventory gameInventoryInstance = GameInventory.getInstance();
    
    @Override
    public void sendMessage(boolean bool, User user) {
        for(User u : this.users){
            if( u != user){
                u.receive(bool);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
    
}
