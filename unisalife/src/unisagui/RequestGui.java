/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

/**
 *
 * @author Davide
 * This class allows  communication between RequestManager other classes without
 * using other thread or active wait
 */
public class RequestGui {
    protected boolean value;

    public RequestGui() {
    }
/**
 * 
 * @return  YES=true / NO=False
 * This method returns the value of the given answer by the user.
 * The method waits until the user chooses a response
 */
    public synchronized boolean getValue() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
        }
        return value;
    }
    /**
     * 
     * @param value is a booelan ( YES of user =true / NO of user =False)
     * This method accept the result of the user's response and calls a notify
     * to alert the thread that the value is ready
     */

    public synchronized void setValue(boolean value) {
        this.value = value;
        this.notifyAll();
    }
}
