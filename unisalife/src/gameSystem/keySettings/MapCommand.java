/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import gameSystem.EndGameState;
import gameSystem.GameStateManager;
import gameSystem.LoadingState;
import gameSystem.MapState;
import gameSystem.PauseState;
import gameSystem.PlayState;
import gameSystem.keySettings.interfaces.ActionCommand;
import gameSystem.keySettings.interfaces.KeyCommand;

/**
 * Class to handle the map's appearance
 *
 * @author Giuseppe De Simone
 */
public class MapCommand extends KeyCommand implements ActionCommand {

    private final GameStateManager stateManager = GameStateManager.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitPlayState(PlayState playState) {
        stateManager.setState(MapState.getInstance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitPauseState(PauseState pauseState) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitLoadingState(LoadingState loadState) {
        player.setVelX(0);
        player.setVelY(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitMapState(MapState mapState) {
        stateManager.setState(PlayState.getInstance());
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void visitEndGameState(EndGameState endGamestate) {
    }
}
