/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import database.Storable;
import gameSystem.Game;
import java.io.Serializable;

/**
 * represents an object of the game(player, character, block, teleport, item).
 *
 * @author simon
 */
public abstract class GameObject implements Storable,Serializable {

    /**
     * width in term of pixel of the object
     */
    protected int width = Game.DIMENSIONSPRITE;

    /**
     * height in term of pixel of the object
     */
    protected int height = Game.DIMENSIONSPRITE;

    /**
     *
     * Position of the object in the map
     */
    protected Position p;

    /**
     *
     */
    protected GameObject() {

    }

    /**
     *
     * @param p position of the object
     */
    public GameObject(Position p) {
        this.p = p;
    }

    /**
     *
     * @return position of the object
     */
    public Position getPosition() {
        return this.p;
    }

    /**
     *
     * @return position of the object scaled with respect to the dimension of sprites of game
     */
    public Position getScaledPosition() {
        return this.p.getScaledPosition();
    }

    /**
     *
     * @param object
     */
    //public abstract void tick(LinkedList<GameObject> object);
    /**
     *
     * @param g
     */
    //public abstract void render(Graphics g);
    //methods getter and setter  
    /**
     *
     * @return
     */
    /**
     *
     * @param x
     */
    /*public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.width;
    }
     */
    /**
     *
     * @return
     */
}
