/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davide
 * This class allows a thread-safe communication between ExamManager and the Exam thread
 */
public class ResultGui {

    protected int value = 0;
    protected int time;

    public ResultGui(int time) {
        this.time = time;
    }
/**
 * 
 * @return 
 * This method returns the value of the given answer. It is put on hold for "time" seconds,
 * if it does not receive the reply within "time" seconds it returns the value 0
 */
    public synchronized int getValue() {
        try {
            this.wait(time*1000);
        } catch (InterruptedException ex) {
        }
        return value;
    }
    /**
     * 
     * @param value 
     * This method accept the result of the user's response and calls a notify
     * to alert the thread that the value is ready
     */

    public synchronized void setValue(int value) {
        this.value = value;
        this.notifyAll();
    }

}
