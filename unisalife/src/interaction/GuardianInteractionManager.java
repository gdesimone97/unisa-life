/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interaction;

import game.Interfaces.Interactable;
import hud.change.DormitoryHudBarChange;
import language.FileTextManager;
import language.Information;
import language.MessageInformation;
import unisagui.GuiManager;

/**
 *
 * @author 1997g
 */
public class GuardianInteractionManager implements InteractionManager {

    @Override
    public void execute(Interactable obj) {
        // get text to show
        FileTextManager tm;
        String toShow = null;

        try {
            tm = FileTextManager.getFileTextManager();
            MessageInformation ms = new MessageInformation("SleepResidences");
            toShow = tm.getString(ms).get(0);

            // show pop up
            GuiManager.getInstance().showDialog(toShow);

            // restore status bars
            DormitoryHudBarChange d = new DormitoryHudBarChange();
            d.execute();
        } catch (Exception ex) {
            // decide what to do when an error with string retriving occurs
        }
    }
}
