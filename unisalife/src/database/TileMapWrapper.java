/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import game.GameObjects.Position;
import game.GameResources.TileMap;
import java.io.Serializable;
import org.dizitart.no2.objects.Id;

/**
 * Wrapper class to mantain a TileMap object without loading its images
 * @author Alfonso De Masi
 */
public class TileMapWrapper implements Storable, Serializable {

    @Id
    private int id;
    private String p;
    private String m;
    private int l;
    private int w;
    private Position pos;
    private String minimapPath;
    
    
    /**
     * Constructor of the wrapped class
     * @param id Map id integer
     * @param l length
     * @param w width
     * @param p path of the tileset
     * @param m path of the map
     * @param pos initial position of the player in the map
     * @param minimapPath path of the mini-map related to the map
     */
    public TileMapWrapper(int id, int l, int w, String p, String m, Position pos, String minimapPath) {
        this.id = id;
        this.p = p;
        this.m = m;
        this.l = l;
        this.w = w;
        this.pos = pos;
        this.minimapPath = minimapPath;
    }

    private TileMapWrapper() {

    }
 
    
    /**
     * Method to obtain a loaded TileMap from the Wrapper
     * @return TileMap object
     */
    public TileMap buildTileMap() {
        return new TileMap(id, l, w, p, m, pos, minimapPath);
    }
    
    /**
     * Getter of the miniMap
     * @return string of the path of the minimap
     */
    public String getMiniMapPath(){
        return this.minimapPath;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public String getIndex() {
        return Integer.toString(this.id);
    }

}
