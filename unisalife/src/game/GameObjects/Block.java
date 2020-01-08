/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.io.Serializable;

/**
 * A block represent a point in the map that can't be passed from the player.
 *
 * @author simon
 */
public class Block extends GameObject implements Serializable {

    /**
     * constructor returns a new istance of block
     *
     * @param p position of the block
     * @param width width of the block
     * @param height height of the block
     */
    
    public Block(Position p, int width,int height) {
        super(p);
        this.height=height;
        this.width=width;
    }
    
    private Block(){
        
    }
    
    /**
     * costructor
     * @param p position of the block
     */
    public Block(Position p) {
        super(p);
    }

    /**
     * 
     * @return the index of the block in order to access to the Database
     */
    @Override
    public String getIndex() {
        return "Block";
    }

}
