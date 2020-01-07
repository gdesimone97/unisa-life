/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.Interfaces.Interactable;

/**
 * Interface to implement the behaviour that an object should respect when interacting with the player
 * @author 1997g
 */
public interface InteractionManager {

    /**
     * Method to implement the actual series of actions taken by the game.
     * @param obj The game object that starts the interaction
     */
    public void execute(Interactable obj);
}
