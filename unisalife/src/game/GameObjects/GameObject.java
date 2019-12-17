/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import gameSystem.Game;
import java.awt.Rectangle;

/**
 * represents an object of the game(player, character, block, teleport, item).
 *
 * @author simon
 */
public abstract class GameObject {


    protected int width = Game.DIMENSIONSPRITE;
    protected int height = Game.DIMENSIONSPRITE;
    protected Position p;
    /**
     *
     * @param x
     * @param y
     */
    public GameObject(Position p) {
        this.p=p;
    }
    
    public Position getPosition(){
        return this.p;
    } 
    public Position getScaledPosition(){
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
