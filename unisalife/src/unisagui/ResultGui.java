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
 */
public class ResultGui {

    protected int value = 0;
    protected int time;

    public ResultGui(int time) {
        this.time = time;
    }

    public synchronized int getValue() {
        try {
            this.wait(time);
        } catch (InterruptedException ex) {
        }
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
        this.notifyAll();
    }

}
