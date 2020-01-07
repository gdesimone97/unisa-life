/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.mediator;

import java.io.Serializable;
import quests.QuestsManager;

/**
 * Extend this class is useful for using the mechanis based on Mediator Pattern
 * Assign a value to mediator and name, then register the user in the mediator
 * 
 * @author liovi
 */
public abstract class User implements Serializable{

    /**
     * Is an instance of the mediator
     */
    protected transient QuestsManager mediator;

    /**
     * Is a string who represents the user
     */
    protected String name;
    
    /**
     * This method is used to send a message to the Mediator who forwards it
     * to the receiver
     * 
     * @param mess is the message that the class would send
     */
    public abstract void send(Message mess);
	
    /**
     * This method can be used to receive a message 
     * 
     * @param mess is the message that the class can receive
     */
    public abstract void receive(Message mess);
}
