/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Teleport;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

/**
 * Wrapper class to mantain a Teleport object and its map
 * @author alfon
 */
@Indices({
    @Index(value = "map", type = IndexType.NonUnique)
})
public class TeleportWrapper implements Storable{

    private int map;
    private Teleport t;
    
    private TeleportWrapper(){
        
    }
    
    /**
     * Constructor of the wrapper fact
     * @param t teleport object
     * @param map map id integer
     */
    public TeleportWrapper(Teleport t, int map){
        this.t = t;
        this.map = map;
    }
    
    /**
     * Getter for the map wrapped
     * @return the map id
     */
    public int getMap() {
        return map;
    }

    /**
     * Getter for the wrapped teleport
     * @return a teleport object
     */
    public Teleport getTeleport() {
        return t;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndex() {
        return Integer.toString(this.map);
    }
    
}
