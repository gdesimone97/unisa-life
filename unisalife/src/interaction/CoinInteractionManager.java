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

/**
 *
 * @author 1997g
 */
public class CoinInteractionManager implements InteractionManager {

    @Override
    public void execute(Interactable obj) {
        // remove element from the the map
        MapManager.getInstance().getMap().removeObject(((Item)obj).getPosition().getScaledPosition());
        
        // add 1 coin in the status
        StatusManager.getInstance().updateMoney(1);
    }
    
}
