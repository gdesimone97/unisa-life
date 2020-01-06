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
 * @author alfon
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

    public int getId() {
        return id;
    }

    public String getP() {
        return p;
    }

    public String getM() {
        return m;
    }

    public int getL() {
        return l;
    }

    public int getW() {
        return w;
    }
    
    /**
     * Method to obtain a loaded TileMap from the Wrapper
     * @return TileMap object
     */
    public TileMap buildTileMap() {
        return new TileMap(id, l, w, p, m, pos, minimapPath);
    }

    public String getMiniMapPath(){
        return this.minimapPath;
    }

    @Override
    public String getIndex() {
        return Integer.toString(this.id);
    }

}
