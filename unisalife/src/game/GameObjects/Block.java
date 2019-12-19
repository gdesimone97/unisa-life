/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

/**
 * A block represent a point in the map that can't be passed from the player.
 *
 * @author simon
 */
public class Block extends GameObject {

    /**
     * constructor returns a new istance of block
     *
     * @param p
     * @param x x position of the block
     * @param y y position of the block
     * @param i SubjectEnum of the block
     * @param width width of block
     * @param height height of block
     */
    
    public Block(Position p, int width,int height) {
        super(p);
        this.height=height;
        this.width=width;
    }
    
    public Block(Position p) {
        super(p);
        /*try {
        sprite = ImageIO.read(
				getClass().getResourceAsStream("/Sprites/gatto.png")
        );}
        catch (Exception e) {
            System.exit(1);
    }*/
    }

    @Override
    public String getIndex() {
        return "Block";
    }

    /**
     * method tick is void because a block
     *
     * @param object
     */

    /**
     *
     * @param g
     */
    
    /*public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)x,(int)y, width, height);
        //g.drawImage(image, (int)x,(int) y, (int)width, (int)height, null);
    }*/
}
