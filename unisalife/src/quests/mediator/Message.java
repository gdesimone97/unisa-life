/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.mediator;

/**
 * This class represents a simple message with two values
 * @author liovi
 */
public class Message {
    private String id;
    private boolean bool;

    /**
     * Public constructor
     * 
     * @param id represents the id of the item/exam
     * @param bool represent the availability of the item/exam
     */
    public Message(String id, boolean bool) {
        this.id = id;
        this.bool = bool;
    }

    /**
     * Returns the id of the item/exam
     * 
     * @return String who represents the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the availability of the item/exam
     * 
     * @return boolean who represents the availability
     */
    public boolean getBool() {
        return bool;
    }
}
