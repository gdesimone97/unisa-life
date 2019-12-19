/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import character.Status;
import character.StatusManager;
import game.Interfaces.Interactable;
import hud.change.VendingMachineHudBarChange;
import language.FileTextManager;
import language.MessageInformation;
import unisagui.GuiManager;
import unisagui.RequestGui;

/**
 *
 * @author 1997g
 */
public class DistributorInteractionManager implements InteractionManager {

    @Override
    public void execute(Interactable obj) {
        // get text to show
        FileTextManager tm;
        String toShow = null;

        try {
            tm = FileTextManager.getFileTextManager();
            MessageInformation ms;
            

            // check if you have money
            if(Status.getMoney()<1) {
                ms = new MessageInformation("NotEnoughMoney");
                toShow = tm.getString(ms).get(0);
                GuiManager.getInstance().showHint(toShow);
            }
            else {
                // show request
//                ms = new MessageInformation("DistributorRequest");
//                toShow = tm.getString(ms).get(0);
//                RequestGui request = new RequestGui();
//                GuiManager.getInstance().showRequest(toShow, request);
//                if(request.getValue()) {
//                     // restore status bars
                    StatusManager.getInstance().updateMoney(-1);
                    VendingMachineHudBarChange v = new VendingMachineHudBarChange();
                    v.execute();
//                }
            }
        } catch (Exception ex) {
            // decide what to do when an error with string retriving occurs
        }
    }
    
}
