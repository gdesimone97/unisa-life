/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import quests.mediator.Message;
import quests.mediator.User;

/**
 *
 * @author liovi
 */
public abstract class QuestFactory extends User{
    
    

    @Override
    public abstract void send(Message mess);

    @Override
    public abstract void receive(Message mess);
    
}
