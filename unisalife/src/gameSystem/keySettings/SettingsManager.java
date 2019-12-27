/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 * This class declares the methods to set and get keyboard keys used to move the
 * avatar and performs game's actions like to interact with game's objects or to
 * enter in pause modality
 *
 * @author Giuseppe De Simone
 */
public class SettingsManager implements Saveable{

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

    @Override
    public Serializable save() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(moveUp);
        list.add(moveDown);
        list.add(moveRight);
        list.add(moveLeft);
        list.add(interactButton);
        list.add(pauseButton);
        list.add(mapButton);
        return list;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        ArrayList<Integer> list = (ArrayList<Integer>) obj;
        moveUp = list.get(0);
        moveDown = list.get(1);
        moveRight = list.get(2);
        moveLeft = list.get(3);
        interactButton = list.get(4);
        pauseButton = list.get(5);
        mapButton = list.get(6);
    }
}
