/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Block;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

/**
 * Wrapper class to mantain a Block object and its map
 * @author christian
 */
@Indices({
    @Index(value = "map", type = IndexType.NonUnique)
})
class BlockWrapper implements Storable {

    private int map;
    private Block block;
    
    /**
     * Constructor of the class 
     * @param block block object
     * @param map map id integer
     */
    public BlockWrapper(Block block, int map) {
        this.map = map;
        this.block = block;
    }
    
    private BlockWrapper() {

    }
    
    /**
     * Getter for the map wrapped
     * @return map id 
     */
    public int getMap() {
        return map;
    }
    
    /**
     * Getter for the block wrapped
     * @return  block object
     */
    public Block getBlock() {
        return block;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndex() {
        return Integer.toString(this.map);
    }

}
