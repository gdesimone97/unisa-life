/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Interfaces;

/**
 * 
 * @author simon
 */
public interface Tickable {

    /**
     * This method has to be implemented by every GameObject, Player, Map, etc...
     */
    public void tick();
}
