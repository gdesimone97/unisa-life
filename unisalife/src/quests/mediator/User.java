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
public interface User {
    public abstract void send(Message mess);

    public abstract void receive(Message mess);
}
