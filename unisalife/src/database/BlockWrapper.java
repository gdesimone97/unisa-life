/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Block;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

/**
 *
 * @author christian
 */
@Indices({
    @Index(value = "map", type = IndexType.NonUnique)
})
class BlockWrapper implements Storable{
    

    private int map;
    private Block block;
    
    public BlockWrapper( Block block, int map ){
        this.map = map;
        this.block = block;
    }
    
    public BlockWrapper(){
        
    }

    public int getMap() {
        return map;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public String getIndex() {
        return Integer.toString(this.map);
    }
    
    
    
}
