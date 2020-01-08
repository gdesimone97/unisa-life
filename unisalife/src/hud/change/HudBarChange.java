/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud.change;

/**
 * This interface, according to the Strategy Pattern, will be implemented by all the classes
 * that take effect on the changing of the status bar and the money label.
 * @author mariodesio
 */
public interface HudBarChange {
    
    /**
     * This method will be implemented by all the classes that take effect on the changing of the status
     * bar and the money label
     */
    public void execute();
    
}
