/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

/**
 *
 * @author 1997g
 */
public class RequestGui {
    protected boolean value;

    public RequestGui() {
    }
/**
 * 
 * @return 
 * This method returns the value of the given answer. It is put on hold for "time" seconds,
 * if it does not receive the reply within "time" seconds it returns the value 0
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
     * @param value 
     * This method accept the result of the user's response and calls a notify
     * to alert the thread that the value is ready
     */

    public synchronized void setValue(boolean value) {
        this.value = value;
        this.notifyAll();
    }
}
