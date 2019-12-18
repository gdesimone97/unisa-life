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
    private int mapToSpawn;
    private ItemDef id;

    public Item(){
        super(1,1);
        this.info = "info";
    }
    
    public Item(float x, float y, String path, String info, int mts) {
        super(x, y);
        this.mapToSpawn = mts;
        this.info = info;
        //this.id = id;
        /*try {
            facingDownImage = ImageIO.read(
                    getClass().getResourceAsStream(path)
            );
        } catch (Exception e) {
            System.exit(1);
        }
         */
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

    public int getMapToSpawn() {
        return this.mapToSpawn;
    }

    public void setTaken() {
        this.taken = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    @Override
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
        g.drawImage(facingDownImage, (int) x, (int) y, width - 10, height - 10, null);
    }

    @Override
    public void interact() {
        ItemInteractionManager iim = new ItemInteractionManager();
        iim.execute(this);
    }

    public String getID() {
        return this.info;
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
