/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem.keySettings;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 * This class declares the methods to set and get keyboard keys used to move the
 * avatar and performs game's actions like to interact with game's objects or to
 * enter in pause modality
 *
 * @author Giuseppe De Simone
 */
public class SettingsManager implements Saveable {

    private enum Commands {
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT,
        INTERACT,
        PAUSE,
        MAP,
        INVENTORY
    }
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
    Map<Commands, Integer> register = new HashMap<>(8);

    private boolean checkInput(int code) {
        final int A = KeyEvent.VK_A;
        final int Z = KeyEvent.VK_Z;
        final int SPACE = KeyEvent.VK_SPACE;
        final int ZERO = KeyEvent.VK_0;
        final int NINE = KeyEvent.VK_9;
        final int DOWN = KeyEvent.VK_DOWN;
        final int LEFT = KeyEvent.VK_LEFT;
        if (code >= A && code <= Z || code >= ZERO && code <= NINE || code == SPACE || code >= LEFT || code <= DOWN) {
            return true;
        }
        return false;
    }

    private void setKey(Commands c, int key) {
        register.put(c, key);
    }

    private boolean isRegistered(int key) {
        if (register.containsValue(key)) {
            return true;
        }
        return false;
    }

    private SettingsManager() {
        register.put(Commands.MOVE_UP, moveUp);
        register.put(Commands.MOVE_DOWN, moveDown);
        register.put(Commands.MOVE_LEFT, moveLeft);
        register.put(Commands.MOVE_RIGHT, moveRight);
        register.put(Commands.PAUSE, pauseButton);
        register.put(Commands.INTERACT, interactButton);
        register.put(Commands.MAP, mapButton);
        register.put(Commands.INVENTORY, inventoryButton);
    }

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
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMapButton(int mapButton) {
        if (!checkInput(mapButton) || isRegistered(mapButton)) {
            return false;
        }
        setKey(Commands.MAP, mapButton);
        this.mapButton = mapButton;
        return true;
    }

    /**
     *
     * @return an int containing the value of keyboard key pressed
     */
    public int getSaveButton() {
        return saveButton;
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
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMoveUp(int moveUp) {
        if (!checkInput(moveUp) || isRegistered(moveUp)) {
            return false;
        }
        setKey(Commands.MOVE_UP, moveUp);
        this.moveUp = moveUp;
        return true;
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
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMoveDown(int moveDown) {
        if (!checkInput(moveDown) || isRegistered(moveDown)) {
            return false;
        }
        setKey(Commands.MOVE_DOWN, moveDown);
        this.moveDown = moveDown;
        return true;
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
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMoveLeft(int moveLeft) {
        if (!checkInput(moveLeft) || isRegistered(moveLeft)) {
            return false;
        }
        setKey(Commands.MOVE_LEFT, moveLeft);
        this.moveLeft = moveLeft;
        return true;
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
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setMoveRight(int moveRight) {
        if (!checkInput(moveRight) || isRegistered(moveRight)) {
            return false;
        }
        setKey(Commands.MOVE_RIGHT, moveRight);
        this.moveRight = moveRight;
        return true;
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
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setInteractButton(int interactButton) {
        if (!checkInput(interactButton) || isRegistered(interactButton)) {
            return false;
        }
        setKey(Commands.INTERACT, interactButton);
        this.interactButton = interactButton;
        return true;
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
     * @return false if key is already setted or it is a no valid value
     * otherwise true
     */
    public boolean setPauseButton(int pauseButton) {
        if (!checkInput(pauseButton) || isRegistered(pauseButton)) {
            return false;
        }
        setKey(Commands.PAUSE, pauseButton);
        this.pauseButton = pauseButton;
        return true;
    }

    @Override
    public Serializable save() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(moveUp);
        list.add(moveDown);
        list.add(moveRight);
        list.add(moveLeft);
        list.add(interactButton);
        list.add(pauseButton);
        list.add(mapButton);
        list.add(this.register);
        return list;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        ArrayList<Object> list = (ArrayList<Object>) obj;
        moveUp = (int) list.get(0);
        moveDown = (int) list.get(1);
        moveRight = (int) list.get(2);
        moveLeft = (int) list.get(3);
        interactButton = (int) list.get(4);
        pauseButton = (int) list.get(5);
        mapButton = (int) list.get(6);
        this.register = (Map<Commands, Integer>) list.get(7);
    }
}
