/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import gameSystem.Game;
import java.io.Serializable;

/**
 * Position represents a tuple of x and y coordinates used for updating the
 * position of the player in the map.
 *
 * @author simon
 */

public class Position implements Serializable {

    private int x = 0;
    private int y = 0;

    /**
     *
     * @param x parameter x of the position
     * @param y parameter y of the position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Position() {

    }

    /**
     *  
     * @return x of position
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return y of position
     */
    public int getY() {
        return y;
    }

    /**
     * setter method for x 
     * @param x new x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setter method for y
     * @param y new y
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x:" + x + " y:" + y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.x;
        hash = 23 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return position of the object scaled by a factor equal to the dimension of the sprites of the game
     */
    public Position getScaledPosition() {
        return new Position(this.x / Game.DIMENSIONSPRITE, this.y / Game.DIMENSIONSPRITE);
    }

}
