/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import java.awt.event.KeyEvent;

/**
 * This class declares the methods to set and get keyboard keys used to move the
 * avatar and performs game's actions like to interact with game's objects or to
 * enter in pause modality
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
    private int mapButton = KeyEvent.VK_M;
    private final int saveButton = KeyEvent.VK_S;
    private final int inventoryButton = KeyEvent.VK_TAB;

    /**
     *
     * @return an int containing the value of keyboard key pressed
     */
    public int getInventoryButton() {
        return inventoryButton;
    }

    /**
     *
     * @return an int containing the value of keyboard key pressed
     */
    public int getMapButton() {
        return mapButton;
    }

    /**
     * set keyboard's key when the player want to visualize the map
     *
     * @param mapButton
     */
    public void setMapButton(int mapButton) {
        this.mapButton = mapButton;
    }

    /**
     *
     * @return an int containing the value of keyboard key pressed
     */
    public int getSaveButton() {
        return saveButton;
    }

    private SettingsManager() {
    }

    /**
     *
     * @return the instance of this class
     */
    public static SettingsManager getSettingsManager() {
        return instance;
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getMoveUp() {
        return moveUp;
    }

    /**
     * set keyboard's key related to move up command
     *
     * @param moveUp
     */
    public void setMoveUp(int moveUp) {
        this.moveUp = moveUp;
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getMoveDown() {
        return moveDown;
    }

    /**
     * set keyboard's key related to move down command
     *
     * @param moveDown
     */
    public void setMoveDown(int moveDown) {
        this.moveDown = moveDown;
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getMoveLeft() {
        return moveLeft;
    }

    /**
     * set keyboard's key related to move left command
     *
     * @param moveLeft
     */
    public void setMoveLeft(int moveLeft) {
        this.moveLeft = moveLeft;
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getMoveRight() {
        return moveRight;
    }

    /**
     * set keyboard's key related to move right command
     *
     * @param moveRight
     */
    public void setMoveRight(int moveRight) {
        this.moveRight = moveRight;
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getInteractButton() {
        return interactButton;
    }

    /**
     * set keyboard's key related to interact command command
     *
     * @param interactButton
     */
    public void setInteractButton(int interactButton) {
        this.interactButton = interactButton;
    }

    /**
     *
     * @return an int containing the value of keyboard key related to this
     * commad
     */
    public int getPauseButton() {
        return pauseButton;
    }

    /**
     * set keyboard's key related to pause command command
     *
     * @param pauseButton
     */
    public void setPauseButton(int pauseButton) {
        this.pauseButton = pauseButton;
    }

}
