/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import saving.Saveable;
import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import interaction.ItemInteractionManager;
import language.Information;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import org.dizitart.no2.objects.Id;
import quests.ItemDef;

/**
 *
 * @author simon
 */
public class Item extends GameObject implements Renderable, Interactable, Serializable, Comparable<Item>, Information, Saveable {
    @Id
    private final String info;
    private BufferedImage facingDownImage;
    private LocalDateTime taken;
    private ItemDef id;

    public Item(Position p,String path, String info,ItemDef id) {
        super(p);
        this.id = id;
        this.info = info;
    }	
		
    public Item(){
        super(new Position(1,1));
        this.info = "info";
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Item)) {
            return false;
        }
        return this.id.equals(((Item) o).getID());
    }

    @Override
    public int compareTo(Item o) {
        if (o == null) {
            return 1;
        }
        return id.compareTo(o.getID());
    }

    public void setTaken() {
        this.taken = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public String getInfo() {
        return this.info;
    }

    public LocalDateTime getTaken() {
        return taken;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "Object : " + this.info + " ( taken in " + this.taken + " )";
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(facingDownImage, this.p.getX(),this.p.getY(), width, height, null);
    }

    @Override
    public void interact() {
        ItemInteractionManager iim = new ItemInteractionManager();
        iim.execute(this);
    }

    public ItemDef getID() {
        return this.id;
    }

    @Override
    public Serializable save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(Serializable obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
