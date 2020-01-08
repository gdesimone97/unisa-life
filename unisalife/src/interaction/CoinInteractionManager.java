/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import character.StatusManager;
import game.GameObjects.Item;
import game.Interfaces.Interactable;
import gameSystem.map.MapManager;
import jubox.JukeBoxSound;

/**
 * Interaction manager used when the player picks up a coin
 * @author 1997g
 */
public class CoinInteractionManager implements InteractionManager {

    /**
     * execute method for the related object
     * @param obj 
     */
    @Override
    public void execute(Interactable obj) {
        // remove element from the the map
        MapManager.getInstance().getMap().removeObject(((Item)obj).getPosition().getScaledPosition());
        
        JukeBoxSound.getInstance().play("money");
        
        // add 1 coin in the status
        StatusManager.getInstance().updateMoney(1);
    }
    
}
