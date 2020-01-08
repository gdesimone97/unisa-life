/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import gameSystem.Game;
import game.Interfaces.Tickable;

/**
 * Camera represents the object that follows the player in the game.
 *
 * @author simon
 */
public class Camera implements Tickable {
    private int x,y;
    private Player player;

    /**
     *
     * @param x x starting position of camera
     * @param y y starting position of camera
     * @param p player to follow
     */
    public Camera(int x,int y,Player p){
        this.x=x;
        this.y=y;
        player=p;
    }

    /**
     * Setter method for x parameter
     * @param x
     */
    public void setX(int x){
        this.x=x;
    }

    /**
     *  Setter method for y parameter
     * @param y
     */
    public void setY(int y){
        this.y=y;
    }
    
    /**
     *  Getter method for x parameter
     * @return
     */
    public float getX(){
        return x;
    }
    
    /**
     * Getter method for y parameter
     * @return
     */
    public float getY(){
        return y;
    }
    
    /** tick method is used for update the position of camera in order to 
     * translate the visual and the objects to be rendered in the game.
     *
     * 
     */
    @Override
    public void tick(){
        x=(-player.getX()+Game.WIDTHSCREEN/2);
        y=(-player.getY()+Game.HEIGHTSCREEN2/2);
        
    }
    
}
