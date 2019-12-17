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
class MoveLeftCommand extends KeyCommand implements MovingCommand{

    @Override
    public void visitPlayState(PlayState playState) {
        player.setVelX(-speed);
        player.setVelY(0);
    }

    @Override
    public void visitPauseState(PauseState pauseState) {

    }

}