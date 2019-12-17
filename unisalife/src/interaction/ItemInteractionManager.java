/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.GameObjects.GameInventorySingleton;
import game.GameObjects.GameObject;
import game.Interfaces.Interactable;
import game.GameObjects.Item;
import gameSystem.Game;
import gameSystem.GameManager;
import gameSystem.map.MapManager;
import language.*;
import language.exceptions.FileTextManagerException;
import language.exceptions.TextFinderException;
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
            
            // show pop up (should be shown until the user says so)
            GuiManager.getInstance().showHint(toShow);

            // add element to the inventory
            GameInventorySingleton.getInstance().addItem((Item) obj);

            // remove element from the the map
            MapManager.getInstance().getMap().removeObject(((Item)obj).getPosition());
        } catch (Exception ex) {
            // decide what to do when an error with string retriving occurs
        }
    }

}
