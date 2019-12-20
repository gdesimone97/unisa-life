/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import game.GameObjects.Player;
import gameSystem.Game;
import gameSystem.LoadingState;
import gameSystem.PauseState;
import gameSystem.PlayState;

/**
 * Abstract class that contain methods to handle keyboard commands respect to
 * the game state
 *
 * @author Giuseppe De Simone
 */
public abstract class KeyCommand {

    protected final int speed = Game.PLAYERSPEED;
    protected final Player player = Player.getIstance();

    /**
     * Handle the keyboard commands when the game is in the play state
     *
     * @param playState
     */
    public abstract void visitPlayState(PlayState playState);

    /**
     * Handle the keyboard commands when the game is in the pause state
     *
     * @param pauseState
     */
    public abstract void visitPauseState(PauseState pauseState);

    /**
     * Handle the keyboard commands when the game is in the loading state
     *
     * @param loadState
     */
    public abstract void visitLoadingState(LoadingState loadState);

}
