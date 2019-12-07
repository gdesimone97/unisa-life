/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.mediator;

/**
 *
 * @author liovi
 */
public abstract class User {
    protected QuestMessages questMessages;
    protected boolean bool;
	
    public User(QuestMessages questMess, boolean value){
            this.questMessages=questMess;
            this.bool=value;
    }

    public abstract void send(boolean bool);

    public abstract void receive(boolean bool);
}
