/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.GameObjects.GameInventory;
import game.Interfaces.Interactable;
import game.GameObjects.Item;
import gameSystem.map.MapManager;
import language.*;
import sound.JukeBoxSound;
import unisagui.GuiManager;

/**
 * InteractionManager implemented for Item collection
 *
 * @author alfon
 */
public class ItemInteractionManager implements InteractionManager {

    @Override
    public void execute(Interactable obj) {
        // get text to show
        FileTextManager tm;
        String toShow = null;

        try {
            tm = FileTextManager.getFileTextManager();
            MessageInformation ms = new MessageInformation("ItemFound");
            toShow = tm.getString(ms).get(0) + " " + tm.getString((Information) obj).get(0);
            
            JukeBoxSound.getInstance().play("collect");
            
            // show pop up (should be shown until the user says so)
            GuiManager.getInstance().showHint(toShow);

            // add element to the inventory and remove from the map
            GameInventory.getInstance().addItem((Item) MapManager.getInstance().getMap().removeObject(((Item)obj).getPosition().getScaledPosition()));
        } catch (Exception ex) {
            // decide what to do when an error with string retriving occurs
        }
    }

}
