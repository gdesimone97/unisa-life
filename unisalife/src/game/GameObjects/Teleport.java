/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import interaction.InteractionManager;
import interaction.TeleportInteractionManager;

/**
 * this class represent a teleport that allows player to change map in the game
 * @author simon
 */
public class Teleport extends GameObject implements Interactable {

    private int mapDest;
    private Position d;

    /**
     **Class Teleport represents a point in the map that allows the player to
     * access in a new zone.
     *
     * @param x x position of the teleport in the map.
     * @param y y position of the teleport in the map
     * @param i objectId of the teleport
     * @param map int that represent the index of new map in the array of maps
     * of game.
     * @param d destination in terms of x and y of the player in the new map
     */
    private Teleport() {
        super();
    }

    /**
     *
     * @param p position of the teleport
     * @param map map in which the player will move
     * @param d initial position of the player in new map
     */
    public Teleport(Position p, int map, Position d) {
        super(p);
        mapDest = map;
        this.d = d;
    }

    
    /**
     *
     * @return destination of the teleport
     */
    public Position getPositionDestination() {
        return d;
    }

    /**
     *
     * @return index of map of destination
     */
    public int getMapDestination() {
        return mapDest;
    }

    /**
     * this method executes the change of map
     */
    @Override
    public void interact() {
        InteractionManager im = new TeleportInteractionManager();
        im.execute(this);
    }

    /**
     *
     * @return index of teleport in order to access to the Database
     */
    @Override
    public String getIndex() {
        return "Teleport";
    }

}
