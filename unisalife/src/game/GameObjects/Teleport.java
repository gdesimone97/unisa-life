/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.GameObjects.Destination;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author simon
 */
public class Teleport extends GameObject {

    private int mapDest;
    private Destination d;

    /**
     **Class Teleport represents a point in the map that allows the player to
     * access in a new zone.
     *
     * @param x x position of the teleport in the map.
     * @param y y position of the teleport in the map
     * @param i objectId of the teleport
     * @param t string that represent the path of the tileset to be loaded
     * @param map int that represent the index of new map in the array of maps
     * of game.
     * @param d destination in terms of x and y of the player in the new map
     */
    public Teleport(float x, float y, String t, int map, Destination d) {
        super(x, y);
        mapDest = map;
        this.d = d;
    }

    //render is void because a Teleport is invisible.
    /**
     *
     * @param g
     */
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }
    //tick is void because the collision is handled by Player's tick method.

    /**
     *
     * @param objects
     */
    public void tick(LinkedList<GameObject> objects) {
    }

    /**
     *
     * @return destination of the teleport
     */
    public Destination getDestination() {
        return d;
    }

    /**
     *
     * @return index of map of destination
     */
    public int getMapDest() {
        return mapDest;
    }

}
