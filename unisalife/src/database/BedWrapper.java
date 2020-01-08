/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Bed;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

/**
 * Wrapper class to mantain a Bed object and its map
 * @author Alfonso De Masi
 */
@Indices({
    @Index(value = "map", type = IndexType.NonUnique)
})
public class BedWrapper implements Storable {

    private Bed bed;
    private int map;

    private BedWrapper() {

    }

    /**
     * Constructor of the class
     * @param bed bed object
     * @param map integer as the id of the map
     */
    public BedWrapper(Bed bed, int map) {
        this.bed = bed;
        this.map = map;
    }

    /**
     * Getter for the bed
     * @return the bed object wrapped
     */
    public Bed getBed() {
        return bed;
    }

    /**
     * Getter for the map
     * @return the map id wrapped
     */
    public int getMap() {
        return map;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndex() {
        return Integer.toString(this.map);
    }

}
