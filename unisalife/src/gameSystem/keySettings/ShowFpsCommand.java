/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import gameSystem.EndGameState;
import gameSystem.Game;
import gameSystem.GameManager;
import gameSystem.keySettings.interfaces.ActionCommand;
import gameSystem.keySettings.interfaces.KeyCommand;
import gameSystem.LoadingState;
import gameSystem.MapState;
import gameSystem.PauseState;
import gameSystem.PlayState;

/**
 * Class to handle the pause key command
 *
 * @author Giuseppe De Simone
 */
public class ShowFpsCommand extends KeyCommand implements ActionCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitPlayState(PlayState playState) {
        Game g = GameManager.getInstance().getGame();
        if(g.getShowFPS())
            g.setShowFPS(false);
        else
            g.setShowFPS(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitPauseState(PauseState pauseState) {
        Game g = GameManager.getInstance().getGame();
        if(g.getShowFPS())
            g.setShowFPS(false);
        else
            g.setShowFPS(true);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitLoadingState(LoadingState loadState) {
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
