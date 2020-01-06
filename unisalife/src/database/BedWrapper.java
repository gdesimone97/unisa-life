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
 * @author alfon
 */
@Indices({
    @Index(value = "map", type = IndexType.NonUnique)
})
public class BedWrapper implements Storable {

    private Bed bed;
    private int map;

    private BedWrapper() {

    }

    public BedWrapper(Bed bed, int map) {
        this.bed = bed;
        this.map = map;
    }

    public Bed getBed() {
        return bed;
    }

    public int getMap() {
        return map;
    }

    @Override
    public String getIndex() {
        return Integer.toString(this.map);
    }

}
