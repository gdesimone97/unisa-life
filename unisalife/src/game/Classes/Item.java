/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.Classes;
import static game.Classes.GameObject.width;
import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
/**
 *
 * @author simon
 */
public class Item extends GameObject implements Renderable,Interactable {
    private String nameItem;
    private BufferedImage facingDownImage;
    public Item(float x,float y,ObjectId i,String path,String nameItem){
        super(x,y,i);
        this.nameItem=nameItem;
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
    
    @Override
    public void interact(){
        System.out.println("hai raccolto "+nameItem);
        Game.maps[Game.actualMap].removeObject(this);
    }
    
}

