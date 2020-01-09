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
import jukebox.JukeBoxSound;
import unisagui.GuiManager;

/**
 * InteractionManager used to interact with the items
 *
 * @author alfon
 */
public class ItemInteractionManager implements InteractionManager {

    /**
     * execute method for the related object
     * @param obj 
     */
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
            ex.printStackTrace();
        }
    }

}
