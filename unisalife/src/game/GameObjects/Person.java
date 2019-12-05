/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
/**
 *
 * @author simon
 */
public class Person extends GameObject implements Renderable,Interactable {
    protected BufferedImage facingDownImage;
    
    public Person(float x,float y,ObjectId i,String path){
        super(x,y,i);
        try {
        facingDownImage = ImageIO.read(
				getClass().getResourceAsStream(path)
        );}
        catch (Exception e) {
            System.exit(1);
    }
    }
        
        @Override
        public void render(Graphics g){
            g.drawImage(facingDownImage, (int)x, (int)y, width, height, null);
    }
        /*@Override
        public void tick(LinkedList<GameObject> l){
            
    }
    */
    @Override
    public void interact(){
        System.out.println("ue fijm");
    }
    
}
