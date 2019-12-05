/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

/**
 * Destination represents a tuple of x and y coordinates used for 
 * updating the position of the player in the map.
 * @author simon
 */
public class Destination {
    private float x=0;
    private float y=0;
    
    /**
     * Takes in input the two coordinates for the new position of the player and creates the destination object
     * @param x x coordinate of the updating position of the player
     * @param y y coordinate of the updating position of the player
     */
    public Destination(float x,float y){
        this.x=x;
        this.y=y;
    }

    /**
     * Returns the x coordinate of the updating position of the player
     * @return
     */
    public float getX(){
        return x;
    }
    
    
    /**
     * Returns the y coordinate of the updating position of the player
     * @return
     */
    public float getY(){
        return y;
    }
    
}
