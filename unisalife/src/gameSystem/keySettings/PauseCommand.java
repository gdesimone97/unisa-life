/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import gameSystem.keySettings.interfaces.ActionCommand;
import gameSystem.keySettings.interfaces.KeyCommand;
import gameSystem.GameStateManager;
import gameSystem.LoadingState;
import gameSystem.MapState;
import gameSystem.PauseState;
import gameSystem.PlayState;

/**
 * Class to handle the pause key command
 *
 * @author Giuseppe De Simone
 */
public class PauseCommand extends KeyCommand implements ActionCommand {

    private final GameStateManager stateManager = GameStateManager.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitPlayState(PlayState playState) {
        stateManager.setState(PauseState.getInstance());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitPauseState(PauseState pauseState) {
        stateManager.setState(PlayState.getInstance());
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
}
