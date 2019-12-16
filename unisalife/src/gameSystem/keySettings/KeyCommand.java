/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import game.GameObjects.Player;
import gameSystem.Game;
import gameSystem.PauseState;
import gameSystem.PlayState;

/**
 *
 * @author Giuseppe De Simone
 */
public abstract class KeyCommand {

    protected final int speed = Game.PLAYERSPEED;
    protected final Player player = Player.getIstance();

    public abstract void visitPlayState(PlayState playState);

    public abstract void visitPauseState(PauseState pauseState);

}
