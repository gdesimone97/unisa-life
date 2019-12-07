/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.mediator;

import quests.QuestsManagerSingleton;

/**
 * Extend this class is useful for using the mechanis based on Mediator Pattern
 * Assign a value to mediator and name, then register the user in the mediator
 * 
 * @author liovi
 */
public abstract class User {
    protected QuestsManagerSingleton mediator;
    protected String name;
    
    public abstract void send(Message mess);
	
    public abstract void receive(Message mess);
}
