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
public abstract class Person extends GameObject implements Renderable, Interactable {

    protected BufferedImage facingDownImage;
    protected Position p;
    
    public Person(Position p, String path) {
        this.p=p;
        try {
            facingDownImage = ImageIO.read(
                    getClass().getResourceAsStream(path)
            );
        } catch (Exception e) {
            System.exit(1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(facingDownImage, p.getX(), p.getY(), width, height, null);
    }
    /*@Override
        public void tick(LinkedList<GameObject> l){
            
    }
     */

}
