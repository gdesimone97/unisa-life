/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import gameSystem.keySettings.interfaces.MovingCommand;
import gameSystem.keySettings.interfaces.KeyCommand;
import gameSystem.GameState;
import gameSystem.GameStateManager;
import gameSystem.keySettings.interfaces.ActionCommand;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class allow to component that add it to listen keyboard keys
 *
 * @author Giuseppe De Simone
 */
public class HandlerInput extends KeyAdapter {

    private final KeyCommand moveUp = new MoveUpCommand();
    private final KeyCommand moveDown = new MoveDownCommand();
    private final KeyCommand moveLeft = new MoveLeftCommand();
    private final KeyCommand moveRight = new MoveRightCommand();
    private final KeyCommand interactCommand = new InteractCommand();
    private final KeyCommand pauseCommand = new PauseCommand();
    private final KeyCommand doNothing = new DoNothingCommand();
    private final KeyCommand saveCommand = new SavingCommand();
    private final KeyCommand mapCommand = new MapCommand();
    private final GameStateManager stateManager = GameStateManager.getInstance();
    private final SettingsManager settingsManager = SettingsManager.getSettingsManager();
    private KeyCommand prev = null;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(KeyEvent e) {
        KeyCommand cmd = selectCommand(e);
        if ((cmd != null && prev != null) || cmd instanceof ActionCommand ) {
            prev = null;
            GameState state = stateManager.getState();
            if (cmd instanceof MovingCommand) {
                state.handleInput(doNothing);
            } else {
                state.handleInput(cmd);
            }
        }

    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent e) {
         KeyCommand cmd = selectCommand(e);
        /* if (e.getKeyCode() == KeyEvent.VK_O) {
            try {
                SaveManager.getSaveManager().load();
            } catch (LoadingException ex) {
                ex.printStackTrace();
            }
        } */
        if (cmd != null && cmd instanceof MovingCommand && (prev == cmd || prev == null)) {
            prev = cmd;
            GameState state = stateManager.getState();
            state.handleInput(cmd);
        }
    }

    private KeyCommand selectCommand(KeyEvent e) {
        int keyCode = e.getKeyCode();
        /*if ((((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) && e.getKeyCode() == settingsManager.getSaveButton())) {
            return saveCommand;
        } */
        if (keyCode == settingsManager.getMoveUp()) {
            return moveUp;
        } else if (keyCode == settingsManager.getMoveDown()) {
            return moveDown;
        } else if (keyCode == settingsManager.getMoveLeft()) {
            return moveLeft;
        } else if (keyCode == settingsManager.getMoveRight()) {
            return moveRight;
        } else if (keyCode == settingsManager.getInteractButton()) {
            return interactCommand;
        } else if (keyCode == settingsManager.getPauseButton()) {
            return pauseCommand;
        } else if (keyCode == settingsManager.getMapButton()) {
            return mapCommand;
        } else {
            return null;
        }
    }

}
