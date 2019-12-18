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
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public abstract class Person extends GameObject implements Renderable, Interactable {

    transient protected BufferedImage facingDownImage;

    public Person(Position p,String path) {
        super(p);
        try {
            facingDownImage = ImageIO.read(
                    getClass().getResourceAsStream(path)
            );
        } catch (Exception e) {
            System.exit(1);
        }
    }
    
    protected Person(){
        super();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(facingDownImage, p.getX(), p.getY(), width, height, null);
    }
    /*@Override
        public void tick(LinkedList<GameObject> l){
            
    }
     */
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ImageIO.write(facingDownImage, "png", (ObjectOutputStream) out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ObjectInputStream ins = (ObjectInputStream) in;
        this.facingDownImage = ImageIO.read(ins);
    }

}
