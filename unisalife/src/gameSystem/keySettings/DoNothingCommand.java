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
public class DoNothingCommand extends KeyCommand {

    @Override
    public void visitPlayState(PlayState playState) {
        player.setVelX(0);
        player.setVelY(0);
        System.out.println("stop");
    }

    @Override
    public void visitPauseState(PauseState pauseState) {

    }

}
