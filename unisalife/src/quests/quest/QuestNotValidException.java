/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

/**
 *
 * @author cmarino
 */
public class QuestNotValidException extends Exception {

    /**
     * Creates a new instance of <code>QuestNotValidException</code> without
     * detail message.
     */
    public QuestNotValidException() {
    }

    /**
     * Constructs an instance of <code>QuestNotValidException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public QuestNotValidException(String msg) {
        super(msg);
    }
}
