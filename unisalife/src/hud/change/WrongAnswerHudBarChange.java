/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hud.change;

import character.StatusManager;

/**
 *
 * @author mariodesio
 */
public class WrongAnswerHudBarChange implements HudBarChange {

    @Override
    public void execute() {
        StatusManager.getInstance().updateStress(20);
    }
    
}
