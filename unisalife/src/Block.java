/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public class Block extends GameObject{
    
    
    public Block(float x,float y,ObjectId i){
        super(x,y,i);
        /*try {
        sprite = ImageIO.read(
				getClass().getResourceAsStream("/Sprites/gatto.png")
        );}
        catch (Exception e) {
            System.exit(1);
    }*/
    }
    
    @Override
    public void tick(LinkedList<GameObject> object){
        
    }
    @Override
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)x,(int)y, width, height);
        //g.drawImage(image, (int)x,(int) y, (int)width, (int)height, null);
    }
    
}
