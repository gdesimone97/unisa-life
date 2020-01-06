/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Block;
import game.GameObjects.Teleport;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

/**
 *
 * @author alfon
 */
@Indices({
    @Index(value = "map", type = IndexType.NonUnique)
})
public class TeleportWrapper implements Storable{

    private int map;
    private Teleport t;
    
    public TeleportWrapper(Teleport t, int map){
        this.t = t;
        this.map = map;
    }

    public int getMap() {
        return map;
    }

    public Teleport getTeleport() {
        return t;
    }
    
    @Override
    public String getIndex() {
        return Integer.toString(this.map);
    }
    
}
