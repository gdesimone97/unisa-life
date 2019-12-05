/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import game.GameResources.Game;
import java.awt.Rectangle;
/**
 * Represents an object of the game. It can be a player, a character, a block, a teleport, an item.
 * @author simon
 */
public abstract class GameObject {
    protected float x,y;
    
    protected ObjectId id;
    protected int width=Game.DIMENSIONSPRITE;
    protected int height=Game.DIMENSIONSPRITE;
    
    /**
     *  
     * The constructor takes in input the position that the object will have in the map and the id 
     * associated with it.
     * @param x x coordinate in which the object is situated
     * @param y y coordinate in which the object is situated
     * @param id enum indicating the type of game object passed
     */
    public GameObject(float x,float y, ObjectId id){
        this.x=x;
        this.y=y;
        this.id=id;
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
     * Returns the x position of the object
     * @return
     */
        public float getX(){
        return x;
    }
    
    /**
     * Returns the y position of the object
     * @return
     */
    public  float getY(){
        return y;
        
    }
    
    /**
     * Sets the x position that the object will have in the map
     * @param x
     */
    public void setX(float x){
        this.x=x;
    }
    
    /**
     * Sets the y position that the object will have in the map
     * @param y
     */
    public void setY(float y){
        this.y=y;
    }
    
    /**
     * Returns the id associated with the object
     * @return
     */
    public ObjectId getId() {
        return this.id;
    }
    /*public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.width;
    }
    */

    /**
     *
     * Returns the rectangle that contains the object
     * 
     * @return
     */
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }
    
}
