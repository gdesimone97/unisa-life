/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import gameSystem.PauseState;
import gameSystem.PlayState;

/**
 *
 * @author Giuseppe De Simone
 */
public class InteractCommand extends KeyCommand {

    @Override
    public void visitPlayState(PlayState playState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitPauseState(PauseState pauseState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
