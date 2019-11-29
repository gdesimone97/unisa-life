/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Toolkit;

/**
 *
 * @author simon
 */
public class Camera {
    private float x,y;
    public Camera(float x,float y){
        this.x=x;
        this.y=y;
    }
    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void tick(GameObject player){
        x=(-player.getX()+Game.WIDTHSCREEN/2);
        y=(-player.getY()+Game.HEIGHTSCREEN2/2);
        /*x=(-player.getX()+Toolkit.getDefaultToolkit().getScreenSize().width/2);
        y=(-player.getY()+Toolkit.getDefaultToolkit().getScreenSize().height/2);*/
    }
    
}
