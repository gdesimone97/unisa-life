/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
/**
 *
 * @author simon
 */
public abstract class GameObject {
    protected float x,y;
    protected ObjectId id;
    protected BufferedImage facingDownImage;
    protected static int width=Game.dimensionSprite,height=Game.dimensionSprite;
    
    public GameObject(float x,float y, ObjectId id){
        this.x=x;
        this.y=y;
        this.id=id;
    }
    
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    
    //methods getter and setter  
    public float getX(){
        return x;
    }
    
    public  float getY(){
        return y;
    }
    
    public void setX(float x){
        this.x=x;
    }
    
    public void setY(float y){
        this.y=y;
    }
    
    
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
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }
    
}
