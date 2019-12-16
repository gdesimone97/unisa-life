/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
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
    private final SettingsManager settingsManager = SettingsManager.getSettingsManager();

    @Override
    public void keyReleased(KeyEvent e) {
        doNothing.execute();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyCommand cmd  = selectCommand(e);
        if(cmd != null){
            cmd.execute();
        }
    }

    private KeyCommand selectCommand(KeyEvent e) {
        int keyCode = e.getKeyCode();
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
        } else {
            return null;
        }
    }

}
