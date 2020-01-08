/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import interaction.ItemInteractionManager;
import language.Information;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import org.dizitart.no2.objects.Id;

/**
 * this class represents an item of the game that player can collect in order to
 * pass an exam
 * @author simon
 */
public class Item extends Renderable implements Interactable, Serializable, Comparable<Item>, Information {

    @Id
    private String info;
    private LocalDateTime taken;

    /**
     *
     * @param p position of the object in the map
     * @param path path of the image to be loaded and rendered in the game
     * @param info parameter for Database
     */
    public Item(Position p, String path, String info) {
        super(p, path);
        this.info = info;
    }

    /**
     * costructor
     */
    public Item() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Item)) {
            return false;
        }
        return this.info.equals(((Item) o).getInfo());
    }

    @Override
    public int compareTo(Item o) {
        if (o == null) {
            return 1;
        }
        return info.compareTo(o.getInfo());
    }

    /**
     * this method is used to memorize the moment when the object was collected

     */
    public void setTaken() {
        this.taken = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * 
     * @return the moment in which the object has been collected
     */
    public LocalDateTime getTaken() {
        return taken;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.info);
        return hash;
    }

    @Override
    public String toString() {
        return "Object : " + this.info + " ( taken in " + this.taken + " )";
    }
    
    /**
     * this method allows player to collect the item
     */
    @Override
    public void interact() {
        ItemInteractionManager iim = new ItemInteractionManager();
        iim.execute(this);
    }

    /**
     * 
     * @return the id of the object for Database
     */
    public String getID() {
        return this.info;
    }

    /**
     *
     * @return the id of the object for Database
     */
    @Override
    public String getIndex() {
        return this.info;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.getImage(), this.p.getX(), this.p.getY(), width, height, null);
    }
}
