/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import character.Status;
import character.StatusManager;
import game.GameObjects.Cook;
import game.GameObjects.Player;
import game.Interfaces.Interactable;
import hud.change.CanteenHudBarChange;
import language.FileTextManager;
import language.MessageInformation;
import saving.SaveManager;
import sound.JukeBoxSound;
import unisagui.GuiManager;
import unisagui.RequestGui;

/**
 *
 * @author 1997g
 */
public class CookInteractionManager implements InteractionManager {

    @Override
    public void execute(Interactable obj) {
        // get text to show
        FileTextManager tm;
        String toShow = null;
        String nome = ((Cook) obj).getNome();

        try {
            tm = FileTextManager.getFileTextManager();
            MessageInformation ms;
            
            if(Status.getMoney()<3) {
                ms = new MessageInformation("NotEnoughMoneyCanteen");
                toShow = tm.getString(ms).get(0);
                GuiManager.getInstance().showDialog(nome, toShow);
                JukeBoxSound.getInstance().play("wrong");
            } else {
                // show request
//                ms = new MessageInformation("CanteenRequest");
//                toShow = tm.getString(ms).get(0);
//                RequestGui request = new RequestGui();
//                GuiManager.getInstance().showRequest(toShow, request);
//                if(request.getValue()) {
                     // restore status bars
                    JukeBoxSound.getInstance().play("canteen");
                    StatusManager.getInstance().updateMoney(-3);
//                }

                // show pop up
                ms = new MessageInformation("SeeYouAgainName");
                toShow = tm.getString(ms).get(0) + Player.getIstance().getName();
                GuiManager.getInstance().showDialog(nome, toShow);

                // restore status bars
                CanteenHudBarChange c = new CanteenHudBarChange();
                c.execute();
            }
            
            // autosave
            SaveManager.getSaveManager().save();
        } catch (Exception ex) {
            // decide what to do when an error with string retriving occurs
        }
    }
    
}
