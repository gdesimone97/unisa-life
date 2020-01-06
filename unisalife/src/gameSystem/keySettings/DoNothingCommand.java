/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import gameSystem.EndGameState;
import gameSystem.keySettings.interfaces.KeyCommand;
import gameSystem.LoadingState;
import gameSystem.MapState;
import gameSystem.PauseState;
import gameSystem.PlayState;

/**
 * Class to handle the situation when the playr doesn't press any key
 *
 * @author Giuseppe De Simone
 */
public class DoNothingCommand extends KeyCommand {

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitPlayState(PlayState playState) {
        player.setVelX(0);
        player.setVelY(0);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitPauseState(PauseState pauseState) {

    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitLoadingState(LoadingState loadState) {
        player.setVelX(0);
        player.setVelY(0);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitMapState(MapState mapState) {
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitEndGameState(EndGameState endGamestate) {
    }

}
