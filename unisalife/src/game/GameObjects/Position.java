/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

/**
 * Position represents a tuple of x and y coordinates used for 
 updating the position of the player in the map.
 * @author simon
 */
public class Position {
    private int x=0;
    private int y=0;
    
    /**
     *
     * @param x
     * @param y
     */
    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }

    /**
     *
     * @return
     */
    public int getX(){
        return x;
    }
    
    /**
     *
     * @return
     */
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x=x;
    } 
    
    public void setY(int y){
        this.y=y;
    } 
    
}
