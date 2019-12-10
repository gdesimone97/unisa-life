/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.GameObjects.GameObject;
import game.Interfaces.Interactable;
import game.GameObjects.Item;
import game.GameResources.Game;
import language.*;
import language.exceptions.FileTextManagerException;
import language.exceptions.TextFinderException;
import unisagui.GuiManager;

/**
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
            toShow = tm.getString(new MessageInformation("ItemFound")).get(0);
            toShow.concat(" " + tm.getString((Information) obj).get(0));

        } catch (FileTextManagerException | TextFinderException ex) {
            // decide what to do when an error with string retriving occurs
        }

        // show pop up (should be shown until the user says so)
        GuiManager.getInstance().showHint(toShow, true);

        // add element to the inventory
        GameInventorySingleton.getInstance().addItem((Item) obj);

        // remove element from the the map
        Game.getGame().getActualMap().getList().remove((GameObject) obj);

    }

}
