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
class MoveUpCommand extends KeyCommand {

    @Override
    public void visitPlayState(PlayState playState) {
        player.setVelY(-speed);
    }

    @Override
    public void visitPauseState(PauseState pauseState) {
    }



}
