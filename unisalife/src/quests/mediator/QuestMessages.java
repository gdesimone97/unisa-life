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
public interface QuestMessages {
    public void sendMessage(Message mess, User user);
    void addUser(User user);
}
