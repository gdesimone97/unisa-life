/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public abstract class Person extends GameObject implements Renderable, Interactable {

    transient protected BufferedImage facingDownImage;
    private String path;

    public Person(Position p, String path) {
        super(p);
        this.path = path;
        try {
            facingDownImage = ImageIO.read(
                    getClass().getResourceAsStream(path)
            );
        } catch (Exception e) {
            System.exit(1);
        }
    }

    protected Person() {
        super();
    }
    
    public String getPath(){
        return this.path;
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
