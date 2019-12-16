/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import java.awt.event.KeyEvent;

/**
 *
 * @author Giuseppe De Simone
 */
public class SettingsManager {

    private static final SettingsManager instance = new SettingsManager();
    private int moveUp = KeyEvent.VK_W;
    private int moveDown = KeyEvent.VK_S;
    private int moveLeft = KeyEvent.VK_A;
    private int moveRight = KeyEvent.VK_D;
    private int interactButton = KeyEvent.VK_SPACE;
    private int pauseButton = KeyEvent.VK_P;

    private SettingsManager() {
    }

    public static SettingsManager getSettingsManager() {
        return instance;
    }

    public int getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(int moveUp) {
        this.moveUp = moveUp;
    }

    public int getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(int moveDown) {
        this.moveDown = moveDown;
    }

    public int getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(int moveLeft) {
        this.moveLeft = moveLeft;
    }

    public int getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(int moveRight) {
        this.moveRight = moveRight;
    }

    public int getInteractButton() {
        return interactButton;
    }

    public void setInteractButton(int interactButton) {
        this.interactButton = interactButton;
    }

    public int getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(int pauseButton) {
        this.pauseButton = pauseButton;
    }

}
