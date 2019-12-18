/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.Interfaces.Interactable;
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
            MessageInformation ms = new MessageInformation("DistributorRequest");
            toShow = tm.getString(ms).get(0);
            
            // show pop up
            RequestGui request = new RequestGui();
            GuiManager.getInstance().showRequest(toShow, request);
            if(request.getValue()) {
                // restore status bars
//              BehaviouralStatus b = new DistributorBehaviour();
//              b.execute();
            }
            
        } catch (Exception ex) {
            // decide what to do when an error with string retriving occurs
        }
    }
    
}
