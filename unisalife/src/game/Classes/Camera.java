/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;

import game.Interfaces.Tickable;

/**
 * Camera represents the object that follows the player in the game.
 *
 * @author simon
 */
public class Camera implements Tickable {
    private float x,y;

    /**
     *
     * @param x x starting position of camera
     * @param y y starting position of camera
     */
    public Camera(float x,float y){
        this.x=x;
        this.y=y;
    }

    /**
     *
     * @param x
     */
    public void setX(float x){
        this.x=x;
    }

    /**
     *
     * @param y
     */
    public void setY(float y){
        this.y=y;
    }
    
    /**
     *
     * @return
     */
    public float getX(){
        return x;
    }
    
    /**
     *
     * @return
     */
    public float getY(){
        return y;
    }
    
    /** tick method is used for update the position of camera in order to 
     * translate the visual and the objects to be rendered in the game.
     *
     * @param player The player that camera follows.
     */
    @Override
    public void tick(){
        x=(-Game.player.getX()+Game.WIDTHSCREEN/2);
        y=(-Game.player.getY()+Game.HEIGHTSCREEN2/2);
        /*x=(-player.getX()+Toolkit.getDefaultToolkit().getScreenSize().width/2);
        y=(-player.getY()+Toolkit.getDefaultToolkit().getScreenSize().height/2);*/
    }
    
}
