/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
/**
 * represents an object of the game(player, character, block, teleport, item).
 * @author simon
 */
public abstract class GameObject {
    protected float x,

    /**
     *
     */
    y;

    /**
     *
     */
    protected ObjectId id;

    /**
     *
     */
    
    protected static int width=Game.dimensionSprite,

    /**
     *
     */
    height=Game.dimensionSprite;
    
    /**
     *
     * @param x
     * @param y
     * @param id
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
    public  float getY(){
        return y;
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
     * @return
     */
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }
    
}
