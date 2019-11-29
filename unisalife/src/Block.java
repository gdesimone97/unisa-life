/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author simon
 */
public class Block extends GameObject{
    public Block(float x,float y,ObjectId i){
        super(x,y,i);
    }
    
    public void tick(LinkedList<GameObject> object){
        
    }
    
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)x,(int)y, width, height);
    }
    
}
