/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.Interfaces.Interactable;
import game.Interfaces.Renderable;
import language.Information;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import language.FileTextManager;
import language.TextManager;
import unisagui.GuiManager;

/**
 *
 * @author simon
 */
public class Item extends GameObject implements Renderable, Interactable, Serializable, Comparable<Item>, Information {

    private final String title;
    private final String info;
    private BufferedImage facingDownImage;
    private LocalDateTime taken;

    public Item(float x, float y, ObjectId i, String path, String title, String info) {
        super(x, y, i);
        this.title = title;
        this.info = info;
        try {
            facingDownImage = ImageIO.read(
                    getClass().getResourceAsStream(path)
            );
        } catch (Exception e) {
            System.exit(1);
        }
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

    public void setTaken() {
        this.taken = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    public String getTitle() {
        return title;
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
        hash = 31 * hash + Objects.hashCode(this.info);
        return hash;
    }

    @Override
    public String toString() {
        return "Object : " + this.title + " ( taken in " + this.taken + " )";
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(facingDownImage, (int) x, (int) y, width, height, null);
    }

    @Override
    public void interact() {
        /*
        try {
        FileTextManager textManager = FileTextManager.getFileTextManager();
        String s = textManager.getString(this).get(0);
        GuiManager guiManager = GuiManager.getInstance();
        guiManager.showDialog(s);
        } catch (Exception ex){
            System.out.println("Errore");
        }
        */
        System.out.println("hai raccolto " + title);
    }

    @Override
    public Boolean isAvailable() {
        return true;
    }
}
