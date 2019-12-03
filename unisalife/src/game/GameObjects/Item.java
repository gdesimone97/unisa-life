/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;
import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Objects;
/**
 *
 * @author simon
 */
public class Item extends GameObject implements Renderable,Interactable,Serializable,Comparable<Item> {
    private final String nameItem;
    private BufferedImage facingDownImage;
    private LocalDateTime taken;

    
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
    
    public void setTaken(LocalDateTime t){
        taken=t;
    }
    
    public String getTitle() {
        return nameItem;
    }

   

    public LocalDateTime getTaken() {
        return taken;
    }

   
    @Override
    public boolean equals( Object o ){
        if(o == null || !(o instanceof Item) )
            return false;
        
        return ((Item)o).getTitle().equals(this.nameItem);
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.nameItem);
        return hash;
    }
    
    @Override
    public int compareTo(Item o) {
        if(o.getTitle().equals(nameItem))
            return o.getTaken().compareTo(taken);
        return o.getTitle().compareTo(nameItem);
    }
    
    @Override
    public String toString(){
        return "Object : " + this.nameItem + " ( taken in "+ this.taken +" )";
    }
    
    
   @Override
    public void render(Graphics g){
        g.drawImage(facingDownImage, (int)x, (int)y, width, height, null);
    }
    
    @Override
    public void interact(){
        System.out.println("hai raccolto "+nameItem);
    }
    
}

