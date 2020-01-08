/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.mediator;

/**
 * This interface is used to establish a contract for the mediator
 * 
 * @author liovi
 */
public interface QuestMessages {

    /**
     * This method is used to send the messages to the registered users
     * 
     * @param mess a Message object
     * @param user the User that sends the message
     */
    public void sendMessage(Message mess, User user);

    /**
     * This method is used from users to register itself to the Mediator
     * 
     * @param user the User to register
     */
    void addUser(User user);
}
