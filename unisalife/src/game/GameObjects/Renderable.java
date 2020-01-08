/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Abstract class to manage objects that needs to be rendered. It also provide a
 * method to load the image from the inital path.
 *
 * @author alfon
 */
public abstract class Renderable extends GameObject {

    private String path;
    private transient BufferedImage image;

    /**
     *
     * @param p position of the object
     * @param path path of the image to be loaded and rendered during the game
     */
    public Renderable(Position p, String path) {
        super(p);
        this.path = path;
    }

    /**
     * costructor
     */
    protected Renderable() {
    }

    /**
     * Method to load the image from the path
     *
     * @throws game.GameObjects.ImageNotLoadedException 
     */
    public void loadImage() throws ImageNotLoadedException {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            throw new ImageNotLoadedException();
        }
    }

    /**
     * Getter
     *
     * @return the string of the path
     */
    public String getPath() {
        return this.path;
    }
    
    /**
     * Getter
     * @return the buffered image of the object
     */
    public BufferedImage getImage(){
        return this.image;
    }

    /**
     * Method to be implemented.It provides the mechanism to render the object
 in the game.
     * @param g graphics of the canvas
     */
    public abstract void render(Graphics g);

}
