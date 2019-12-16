/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import gameSystem.GameStateManager;
import gameSystem.PauseState;
import gameSystem.PlayState;

/**
 *
 * @author Giuseppe De Simone
 */
public class PauseCommand extends KeyCommand implements ActionCommand{

    private final GameStateManager stateManager = GameStateManager.getInstance();

    @Override
    public void visitPlayState(PlayState playState) {
        stateManager.setState(PauseState.getInstance());
    }

    @Override
    public void visitPauseState(PauseState pauseState) {
        stateManager.setState(PlayState.getInstance());
    }

}
