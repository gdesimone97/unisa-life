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
public class Message {
    private String id;
    private boolean bool;

    public Message(String id, boolean bool) {
        this.id = id;
        this.bool = bool;
    }

    public String getId() {
        return id;
    }

    public boolean getBool() {
        return bool;
    }
}
